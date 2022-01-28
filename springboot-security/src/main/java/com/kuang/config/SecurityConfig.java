package com.kuang.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    // 认证,  springboot 2.1.X 可以直接使用
    // 密码编码：PasswordEncoder
    // 在Spring Security 5.0+ 新增了很多加密方法~   要加密之后才能使用
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
                .withUser("hyx").password(new BCryptPasswordEncoder().encode("1234")).roles("vip1","vip3")
                .and()
                .withUser("root").password(new BCryptPasswordEncoder().encode("1234")).roles("vip1","vip2","vip3")
                .and()
                .withUser("thy").password(new BCryptPasswordEncoder().encode("1234")).roles("vip2");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 首页所有人可以访问，功能页只能对应的有权限的人才能访问
        // authorizeRequests()  认证请求
        // 请求授权的规则
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/level1/**").hasRole("vip1")
                .antMatchers("/level2/**").hasRole("vip2")
                .antMatchers("/level3/**").hasRole("vip3");

        // 没权限默认会到登录页面
        // .loginPage("/toLogin"); 定制登录页   loginProcessingUrl 登录认证  这里可以写 login.html 的 网址
        http.formLogin().loginPage("/toLogin").usernameParameter("user").passwordParameter("pwd").loginProcessingUrl("/login");


        // 注销，开启了注销功能，跳到首页

        // 防止网站工具； get， post
        http.csrf().disable(); // 关闭csrf功能，登录失败肯存在的原因

        // 注销功能
        http.logout().logoutSuccessUrl("/");


        // 开启记住我功能   cookie,默认保存两周 , 自定义接收前端的参数
        http.rememberMe().rememberMeParameter("remember");
    }
}
