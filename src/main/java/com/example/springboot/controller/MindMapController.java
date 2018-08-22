package com.example.springboot.controller;

import com.example.springboot.entity.MindMap;
import com.example.springboot.service.MindMapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
public class MindMapController {

    @Autowired
    MindMapService mindMapService;

    @PostMapping(value = "/api/mindmap/save")
    public Integer mindMapSave(@RequestBody MindMap mindMap){
        mindMap.setLastUpdateTime(new Date());
        return mindMapService.save(mindMap);
    }

    @GetMapping(value = "/api/mindmap")
    public List<Map<String, Object>> getMindMapTree(){
        return mindMapService.getMindMapTree();
    }

    @GetMapping(value = "/api/mindmap/{id}")
    public MindMap getMindMapTreeById(@PathVariable("id") Integer id){
        return mindMapService.getMindMapById(id);
    }

    @DeleteMapping(value = "/api/mindmap/delete/{id}")
    public void deleteMindMap(@PathVariable("id") Integer id){
        mindMapService.delete(id);
    }
}
