package com.xin.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {
    // 倒着配 从下至上


    // ShiroFilterFactoryBean
    @Bean(name = "shiroFilterFactoryBean")
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("getDefaultWebSecurityManager") DefaultWebSecurityManager DefaultWebSecurityManager) {
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        // 设置安全管理器
        bean.setSecurityManager(DefaultWebSecurityManager);
        // 拦截器
        // 添加shiro的内置过滤器
        /*
            anon：无需认证就可以访问
            authc：必须认证了才能访问
            user：必须拥有   记住我  功能才能用
            perms： 拥有对某个资源的权限才能访问：
            roles： 拥有某个角色权限才能访问
        */
        Map<String,String> filterMap = new LinkedHashMap<>();

        // 授权 ,正常情况下，没有授权会跳转到未授权页面 Unauthorized 未授权
        filterMap.put("/user/add","perms[user:add]");  //perms[user:add] 代表 user 用户，拥有add权限
        filterMap.put("/user/update","perms[user:update]");

        // 设置未授权的请求
        bean.setUnauthorizedUrl("/unauth");


        // 可以使用通配符
        filterMap.put("/user/*","authc");

        // 设置登录的请求
        bean.setLoginUrl("/toLogin");

        bean.setFilterChainDefinitionMap(filterMap);
        return bean;
    }


    // DefaultWebSecurityManager
    @Bean
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // 关联  Realm
        securityManager.setRealm(userRealm);

        return securityManager;
    }



    // 创建  realm  对象 , 需要自定义
    @Bean
    public UserRealm userRealm() {
        return new UserRealm();
    }

    // 整合shiroDialect： 用来整合  shiro  thymeleaf
    @Bean
    public ShiroDialect getShiroDialect() {
        return new ShiroDialect();
    }
}
