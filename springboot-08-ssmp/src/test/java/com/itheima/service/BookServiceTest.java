package com.itheima.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itheima.pojo.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BookServiceTest {

    @Autowired
    private BookService bookService;

    @Test
    void testGetById() {
        bookService.getById(1);
    }

    @Test
    void testSave() {
        Book book = new Book();
        book.setType("数据");
        book.setName("SpringMybatis");
        book.setDescription("测试");
        bookService.save(book);
    }

    @Test
    void testUpdate() {
        Book book = new Book();
        book.setId(3);
        book.setType("c");
        book.setName("c");
        book.setDescription("c");
        bookService.update(book);
    }

    @Test
    void testDelete() {
        bookService.delete(17);
    }

    @Test
    void testGetAll() {
        bookService.getAll();
    }

    @Test
    void testGetPage() {
        bookService.getPage(2,3);
    }

}
