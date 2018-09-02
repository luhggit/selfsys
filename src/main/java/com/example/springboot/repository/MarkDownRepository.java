package com.example.springboot.repository;

import com.example.springboot.entity.MarkDown;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MarkDownRepository extends JpaRepository<MarkDown,Integer> {
    List<MarkDown> findMarkDownsByStatusIsNull();
}
