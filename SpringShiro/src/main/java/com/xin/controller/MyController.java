package com.xin.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MyController {

    @RequestMapping({"/", "/index"})
    public String toIndex(Model model) {
        model.addAttribute("msg", "hello,shiro");

        return "index";
    }

    @RequestMapping("/user/add")
    public String add() {
        return "user/add";
    }

    @RequestMapping("/user/update")
    public String update() {
        return "user/update";
    }

    @RequestMapping("/toLogin")
    public String toLogin() {
        return "login";
    }

    @RequestMapping("/login")
    public String login(String username, String password, Model model) {

        // 获取当前的用户
        Subject subject = SecurityUtils.getSubject();

        // 封装用户的登录数据    放成令牌加密
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try {
            subject.login(token);   // 执行登录方法  没有异常就ok
            return "index";
        } catch (UnknownAccountException uae) {  // 代表用户名不存在
            model.addAttribute("msg", "用户名错误");
            return "login";
        }  catch (IncorrectCredentialsException ice) {  // 代表密码不存在
            model.addAttribute("msg", "密码错误");
            return "login";
        }
    }

    @RequestMapping("/unauth")
    @ResponseBody
    public String Unauth () {
        return "未经授权";
    }
}

