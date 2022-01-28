package com.xin.controller;

import com.xin.pojo.User;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;


@RestController
public class HelloController {
    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    // 只要我们的接口中，返回值存在实体类，他就会被扫描进Swagger中
    @PostMapping("/user")
    public User user() {
        return new User();
    }

    // Operation 接口,不是放在类上的,是方法
    @ApiOperation("Hello,控制类")
    @GetMapping("/user2")
    public String hello2(@ApiParam("用户") String username) {
        return "hello" + username;
    }
}
