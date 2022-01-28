package com.kuang.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RestController
public class HelloController {
    @GetMapping("/t1")
    @ResponseBody
    public String test() {
        return "hello";
    }


}
