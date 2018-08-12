package com.example.springboot.repository;

import com.example.springboot.entity.Girl;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GirlRepository extends JpaRepository<Girl,Integer>{

}
