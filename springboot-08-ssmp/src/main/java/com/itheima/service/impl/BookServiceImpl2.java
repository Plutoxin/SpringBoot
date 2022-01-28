package com.itheima.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itheima.dao.BookMapper;
import com.itheima.pojo.Book;
import com.itheima.service.IBookService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// 定义成业务层  对应的bean   不定义就会报错
@Service
public class BookServiceImpl2 extends ServiceImpl<BookMapper, Book> implements IBookService {

    @Autowired
    private BookMapper iBookService;

    @Override
    public IPage<Book> getPage(int currentPage, int PageSize) {
        IPage<Book> page = new Page(currentPage,PageSize);
        iBookService.selectPage(page,null);
        return page;
    }

    @Override
    public IPage<Book> getPage(int currentPage, int PageSize, Book book) {
        LambdaQueryWrapper<Book> lqw = new LambdaQueryWrapper();
        lqw.like(Strings.isNotEmpty(book.getType()),Book::getType,book.getType());
        lqw.like(Strings.isNotEmpty(book.getName()),Book::getName,book.getName());
        lqw.like(Strings.isNotEmpty(book.getDescription()),Book::getDescription,book.getDescription());

        IPage<Book> page = new Page(currentPage,PageSize);
        iBookService.selectPage(page,lqw);
        return page;
    }
}
