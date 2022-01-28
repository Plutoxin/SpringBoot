package com.it.Controller;

import com.it.Controller.bookDao;
import com.it.springboot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;

@SpringBootTest(classes = springboot.class)
class test {

    @Autowired
//    private bookDao bookts;

    @Test
    void test1() {
//        bookts.book();
        System.out.println("sc");
    }

}

