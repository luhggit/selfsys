package com.example.springboot.service;

import com.example.springboot.entity.MindMap;

import java.util.List;
import java.util.Map;

public interface MindMapService {
    List<Map<String,Object>> getMindMapTree();
    Integer save(MindMap mindMap);
    MindMap getMindMapById(Integer id);
    void update(MindMap mindMap);
    void delete(Integer id);
}
