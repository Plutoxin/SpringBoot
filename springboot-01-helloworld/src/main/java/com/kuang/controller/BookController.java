package com.kuang.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/books")
public class BookController {

    // 使用自动装配将所有对象封装到env里
    @Autowired
    private Environment env;

    @Autowired
    private Data data;


    @GetMapping
    public String test() {
        System.out.println(env.getProperty("server.port"));
        System.out.println(env.getProperty("enterpri.name"));
        System.out.println(env.getProperty("enterpri.age"));
        System.out.println(env.getProperty("enterpri.subject[0]"));
        System.out.println(env.getProperty("enterpri.subject[1]"));
        System.out.println(env.getProperty("enterpri.subject[2]"));
        System.out.println(data.toString());
        return "GoinOn....";
    }
}
