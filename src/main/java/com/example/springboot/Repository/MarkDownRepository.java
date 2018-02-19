package com.example.springboot.Repository;

import com.example.springboot.model.MarkDown;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MarkDownRepository extends JpaRepository<MarkDown,Integer> {
}
