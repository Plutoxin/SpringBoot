package com.xin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.HashMap;

@SpringBootApplication
public class SwaggerApplication {

    public static void main(String[] args)  {
        SpringApplication.run(SwaggerApplication.class, args);
    }

}
