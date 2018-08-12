package com.example.springboot.repository;

import com.example.springboot.entity.MarkDown;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MarkDownRepository extends JpaRepository<MarkDown,Integer> {
}
