package com.example.springboot.repository;

import com.example.springboot.SpringbootApplication;
import com.example.springboot.entity.MarkDown;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringbootApplication.class)
public class MarkDownRepositoryTest {

    @Resource
    private MarkDownRepository markDownRepository;

    /**
     * 测试like
     */
    @Test
    public void testLike() {
        List<MarkDown> markDowns = markDownRepository.findByMdContentLike("%a%");
        System.out.println(markDowns.size());
    }

    /**
     * 测试islike
     */
    @Test
    public void testIslike() {
        List<MarkDown> markDowns = markDownRepository.findMarkDownsByMdContentIsLike("%a%");
        System.out.println(markDowns.size());
    }
}