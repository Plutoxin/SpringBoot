package com.hong;

import com.hong.dao.UserDao;
import com.hong.pojo.user;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class Springboot03MybatisApplicationTests {

    @Autowired
    private UserDao user;

    @Test
    void contextLoads() {
        System.out.println(user.USER(1));
    }

}
