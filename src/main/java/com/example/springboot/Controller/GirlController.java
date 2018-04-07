package com.example.springboot.Controller;

import com.example.springboot.Repository.GirlRepository;
import com.example.springboot.model.Girl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GirlController {

    @Autowired
    private GirlRepository girlRepository;

    @GetMapping(value = "/test/girl")
    public List<Girl> getGrilList(){
        return girlRepository.findAll();
    }
}
