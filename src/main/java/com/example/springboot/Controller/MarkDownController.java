package com.example.springboot.Controller;

import com.example.springboot.Repository.MarkDownRepository;
import com.example.springboot.model.MarkDown;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class MarkDownController {

    @Autowired
    private MarkDownRepository markDownRepository;

    @PostMapping("/api/markdown")
    public void saveMarkDown(@RequestBody MarkDown markDown){
        System.out.println("markdown content:" + markDown.getHtmlContent());
        markDownRepository.save(markDown);
    }

    @GetMapping("/api/markdown")
    public List<Map<String,Object>> getMarkDownTree(){
        List<Sort.Order> orders = new ArrayList<>();
        Sort.Order firstClassOrder = new Sort.Order(Sort.Direction.DESC,"firstClass");
        Sort.Order secondClassOrder = new Sort.Order(Sort.Direction.DESC,"secondClass");
        Sort.Order createTimeOrder = new Sort.Order(Sort.Direction.DESC,"createTime");
        orders.add(firstClassOrder);
        orders.add(secondClassOrder);
        orders.add(createTimeOrder);
        Sort sort = new Sort(orders);
        List<MarkDown> markDowns = markDownRepository.findAll(sort);

        List<Map<String,Object>> result = new ArrayList<>();
        Map<String,Object> map = new HashMap<>();
        Map<String,Object> childMap = new HashMap<>();
        Map<String,Object> secondClassChildMap = new HashMap<>();
        List<Map<String,Object>> children = new ArrayList<>();
        List<Map<String,Object>> childMapChildren = new ArrayList<>();


        Set<String> category = new HashSet<>();

        String firstClass,secondClass;
        int index = 1;

        for (MarkDown markDown:markDowns){
            firstClass = markDown.getFirstClass();
            secondClass = markDown.getSecondClass();

            if (!category.contains(firstClass)){
                category.add(firstClass);
                map = new HashMap<>();
                children = new ArrayList<>();
                map.put("label",firstClass);
                map.put("id",index++);
                map.put("children",children);
                result.add(map);
            }

            if (secondClass!=null&&!"".equals(secondClass.trim())){
                String firstAndSecondClass = firstClass +  "-" + secondClass;
                if (!category.contains(firstAndSecondClass)){
                    category.add(firstAndSecondClass);
                    childMap = new HashMap<>();
                    childMapChildren = new ArrayList<>();
                    childMap.put("id",index++);
                    childMap.put("label",secondClass);
                    childMap.put("children",childMapChildren);
                    children.add(childMap);
                }
                secondClassChildMap = new HashMap<>();
                secondClassChildMap.put("label",markDown.getTitle());
                secondClassChildMap.put("id",markDown.getId());
                childMapChildren.add(secondClassChildMap);
            }else{
                childMap = new HashMap<>();
                childMap.put("label",markDown.getTitle());
                childMap.put("id",markDown.getId());
                children.add(childMap);
            }
        }
        return result;
    }

    @GetMapping("/api/markdown/{id}")
    public MarkDown getMarkDownById(@PathVariable("id") Integer id){
        return markDownRepository.findOne(id);
    }

    @DeleteMapping("/api/markdown/{id}")
    public void deleteMarkdownById(@PathVariable("id") Integer id){
        markDownRepository.delete(id);
    }
}
