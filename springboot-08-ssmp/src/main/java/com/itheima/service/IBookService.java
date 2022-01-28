package com.itheima.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.itheima.pojo.Book;


public interface IBookService extends IService<Book> {
    IPage<Book> getPage(int currentPage,int PageSize);
    IPage<Book> getPage(int currentPage,int PageSize,Book book);
}
