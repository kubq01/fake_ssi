package com.example.ssi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    TestRepo repo;

    @PostMapping
    public void postTest(){
        TestEntity entity = TestEntity.builder().data("test data").build();
        repo.save(entity);
    }

    @GetMapping
    public TestEntity getById(@RequestParam Long id){
        return repo.findById(id).orElseThrow();
    }
}
