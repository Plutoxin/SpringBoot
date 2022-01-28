package com.itheima.controller.utils;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//@ControllerAdvice
@RestControllerAdvice
public class ProjectExceptionAdvice {

    // 拦截所有异常
    // 可以决定抛出哪个异常
    @ExceptionHandler
    public R doException(Exception e) {
        // 记录日志
        // 通知运维
        // 通知开发
        e.printStackTrace();
        return new R("服务器故障，请稍后再试！");
    }
}
