package com.itheima.controller;

import java.io.IOException;
import java.util.List;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.itheima.controller.utils.R;
import com.itheima.pojo.Book;
import com.itheima.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private IBookService bookService;


    @GetMapping
    R getAll() {
        return new R(true, bookService.list());
    }

    // restful风格给出的标准    添加操作
    @PostMapping
    R save(@RequestBody Book book) throws IOException {
        if (book.getName().equals("123")) throw new IOException();
        boolean flag = bookService.save(book);

        return new R(flag,flag?"添加成功^_^":"添加失败-_-!");
    }


    @PutMapping
    R update(@RequestBody Book book) {
        return new R(bookService.updateById(book));
    }

    @DeleteMapping("{id}")
        // 这个把地址栏的值输入给下面的id  必须搭配  PathVariable 使用
    R delete(@PathVariable Integer id) {
        return new R(bookService.removeById(id));
    }

    @GetMapping("{id}")
    R getById(@PathVariable Integer id) {
        return new R(true, bookService.getById(id));
    }

//    @GetMapping("{currentPage}/{PageSize}")
//    R getPage(@PathVariable int currentPage, @PathVariable int PageSize) {
//        IPage<Book> page = bookService.getPage(currentPage, PageSize);
//        if (currentPage > page.getPages()) {
//            page = bookService.getPage((int)page.getPages(), PageSize);
//        }
//        return new R(true, page);
//    }

    @GetMapping("{currentPage}/{PageSize}")
    R getPage(@PathVariable int currentPage, @PathVariable int PageSize,Book book) {
        IPage<Book> page = bookService.getPage(currentPage, PageSize,book);
        if (currentPage > page.getPages()) {
            page = bookService.getPage((int)page.getPages(), PageSize,book);
        }
        return new R(true, page);
    }
}
