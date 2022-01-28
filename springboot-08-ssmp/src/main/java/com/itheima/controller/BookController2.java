package com.itheima.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.itheima.pojo.Book;
import com.itheima.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@RestController   // 注释掉之后，不会被加载成bean
@RequestMapping("/books")
public class BookController2 {
    @Autowired
    private IBookService bookService;


    @GetMapping
    List<Book> getAll() {
        return bookService.list();
    }

    // restful风格给出的标准    添加操作
    @PostMapping
    Boolean save(@RequestBody Book book) {
        return bookService.save(book);
    }


    @PutMapping
    Boolean update(@RequestBody Book book) {
        return bookService.updateById(book);
    }

    @DeleteMapping("{id}")  // 这个把地址栏的值输入给下面的id  必须搭配  PathVariable 使用
    Boolean delete(@PathVariable Integer id) {
        return bookService.removeById(id);
    }

    @GetMapping("{id}")
    Book getById(@PathVariable Integer id) {
        return bookService.getById(id);
    }

    @GetMapping("{currentPage}/{PageSize}")
    IPage<Book> getPage(@PathVariable int currentPage, @PathVariable int PageSize) {
        return bookService.getPage(currentPage, PageSize);
    }
}
