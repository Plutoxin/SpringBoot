package com.kuang.config;

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
    @Bean
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

//        filterMap.put("/user/add","anon");
//        filterMap.put("/user/add","authc");
//        filterMap.put("/user/update","authc");
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


}
