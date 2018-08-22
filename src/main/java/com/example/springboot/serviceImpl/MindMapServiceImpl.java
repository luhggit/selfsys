package com.example.springboot.serviceImpl;

import com.example.springboot.entity.MindMap;
import com.example.springboot.entity.MindMap;
import com.example.springboot.repository.MindMapRepository;
import com.example.springboot.service.MindMapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MindMapServiceImpl implements MindMapService{
    @Autowired
    MindMapRepository mindMapRepository;

    @Override
    public void delete(Integer id) {
        mindMapRepository.delete(id);
    }

    @Override
    public void update(MindMap mindMap) {
        mindMapRepository.save(mindMap);
    }

    @Override
    public Integer save(MindMap mindMap) {
        return mindMapRepository.save(mindMap).getId();
    }

    @Override
    public MindMap getMindMapById(Integer id) {
        return mindMapRepository.findOne(id);
    }

    @Override
    public List<Map<String, Object>> getMindMapTree() {
        List<Sort.Order> orders = new ArrayList<>();
        Sort.Order firstClassOrder = new Sort.Order(Sort.Direction.DESC,"firstClass");
        Sort.Order secondClassOrder = new Sort.Order(Sort.Direction.DESC,"secondClass");
        Sort.Order createTimeOrder = new Sort.Order(Sort.Direction.DESC,"createTime");
        orders.add(firstClassOrder);
        orders.add(secondClassOrder);
        orders.add(createTimeOrder);
        Sort sort = new Sort(orders);
        List<MindMap> MindMaps = mindMapRepository.findAll(sort);

        List<Map<String,Object>> result = new ArrayList<>();
        Map<String,Object> map = new HashMap<>();
        Map<String,Object> childMap = new HashMap<>();
        Map<String,Object> secondClassChildMap = new HashMap<>();
        List<Map<String,Object>> children = new ArrayList<>();
        List<Map<String,Object>> childMapChildren = new ArrayList<>();


        Set<String> category = new HashSet<>();

        String firstClass,secondClass;
        int index = 1;

        for (MindMap mindMap:MindMaps){
            firstClass = mindMap.getFirstClass();
            secondClass = mindMap.getSecondClass();

            if (!category.contains(firstClass)){
                category.add(firstClass);
                map = new HashMap<>();
                children = new ArrayList<>();
                map.put("id",index++);
                map.put("label",firstClass);
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
                secondClassChildMap.put("label",mindMap.getTitle());
                secondClassChildMap.put("id",mindMap.getId());
                childMapChildren.add(secondClassChildMap);
            }else{
                childMap = new HashMap<>();
                childMap.put("label",mindMap.getTitle());
                childMap.put("id",mindMap.getId());
                children.add(childMap);
            }
        }
        return result;
    }
}
