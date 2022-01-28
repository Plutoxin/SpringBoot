package com.xin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
@EnableSwagger2  // 开启Swagger2
public class SwaggerConfig {

    @Bean
    public Docket docket2() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("A");
    }

    @Bean
    public Docket docket3() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("B");
    }

    @Bean
    public Docket docket4() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("C");
    }

    // 配置了swagger的Docket的bean实例
    @Bean
    public Docket docket(Environment environment) {

        // 设置要显示的Swagger环境
        Profiles profiles = Profiles.of("dev", "test");

        // 获取项目的环境
        // 通过environment.acceptsProfiles  判断是否处在自己设定的环境当中
        boolean flag = environment.acceptsProfiles(profiles);

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("延鑫")
                .enable(flag)
                // 一个整体
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.xin.controller"))
//                .paths(PathSelectors.any())
                .build();
    }

    // 配置swagger信息 = apiinfo
    private ApiInfo apiInfo() {

        // 作者信息
        Contact contact = new Contact("洪延鑫", "http://localhost:8080/hello", "1640048467@qq.com");

        return new ApiInfo("鑫的swaggerApi文档"
                , "生活中永远有比你优秀的人在努力"
                , "v1.0"
                , "http://localhost:8080/hello"
                , contact
                , "Apache 2.0"
                , "http://www.apache.org/licenses/LICENSE-2.0"
                , new ArrayList());

    }
}
