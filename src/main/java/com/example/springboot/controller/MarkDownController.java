package com.example.springboot.controller;

import com.example.springboot.repository.MarkDownRepository;
import com.example.springboot.entity.MarkDown;
import com.example.springboot.util.ObjectUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;
import java.util.stream.Collectors;

@RestController
public class MarkDownController {

    @Autowired
    private MarkDownRepository markDownRepository;

    @PostMapping("/api/markdown")
    public Integer saveMarkDown(@RequestBody MarkDown markDown){
        if (markDown.getId() == null) {
            markDown.setCreateTime(new Date());
        }
        if (markDown.getId() != null) {
            MarkDown markDownOld = markDownRepository.getOne(markDown.getId());
            BeanUtils.copyProperties(markDown, markDownOld, ObjectUtils.getNullPropertyNames(markDown));
            markDown = markDownOld;
        }
        markDown.setLastUpdateTime(new Date());
        markDownRepository.save(markDown);
        return markDown.getId();
    }

    @GetMapping("/api/markdown")
    public List<Map<String,Object>> getMarkDownTree(HttpServletRequest request){
        HttpSession httpSession = request.getSession();
        String userName = (String)httpSession.getAttribute("username");

        List<Sort.Order> orders = new ArrayList<>();
        Sort.Order firstClassOrder = new Sort.Order(Sort.Direction.DESC,"firstClass");
        Sort.Order secondClassOrder = new Sort.Order(Sort.Direction.DESC,"secondClass");
        Sort.Order updateTimeOrder = new Sort.Order(Sort.Direction.DESC,"lastUpdateTime");
        orders.add(firstClassOrder);
        orders.add(secondClassOrder);
        orders.add(updateTimeOrder);
        Sort sort = new Sort(orders);


        List<MarkDown> markDowns = null;
//        if ("luhg".equals(userName)){
//            markDowns = markDownRepository.findAll(sort);
//        }else{
//            markDowns = markDownRepository.findMarkDownsByStatusIsNull();
//        }

        markDowns = markDownRepository.findAll(sort);

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
                map.put("firstClass", true);
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
                    childMap.put("secondClass", true);
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

    /**
     * 根据关键字进行搜索
     * @param keyword
     * @return
     */
    @GetMapping("/api/markdown/serach")
    public List<Integer> getMarkDownById(@RequestParam("keyword") String keyword){
        List<MarkDown> markDowns = markDownRepository.findByMdContentLike("%" + keyword + "%");
        return markDowns.stream().map(e -> e.getId()).collect(Collectors.toList());
    }
}
