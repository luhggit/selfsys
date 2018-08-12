package com.example.springboot.controller;

import com.example.springboot.repository.GirlRepository;
import com.example.springboot.entity.Girl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GirlController {

    private static final Logger logger = LoggerFactory.getLogger(GirlController.class);

    @Autowired
    private GirlRepository girlRepository;

    @GetMapping(value = "/test/girl")
    public List<Girl> getGrilList(){
        return girlRepository.findAll();
    }

    @GetMapping(value = "/test/log")
    public void testLog(){
        logger.error("test error");
        try {
            int i = 1/0;
        }catch (Exception e){
            e.printStackTrace();
            logger.error("exception",e);
        }
    }
}
