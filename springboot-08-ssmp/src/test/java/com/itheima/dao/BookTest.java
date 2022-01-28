package com.itheima.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itheima.dao.BookMapper;
import com.itheima.pojo.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BookTest {

    @Autowired
    private BookMapper bookDao;


    @Test
    void testGetById() {
       bookDao.selectById(1);
    }

    @Test
    void testSave() {
        Book book = new Book();
        book.setType("数据");
        book.setName("SpringMybatis");
        book.setDescription("测试");
        bookDao.insert(book);
    }

    @Test
    void testUpdate() {
        Book book = new Book();
        book.setId(3);
        book.setType("c");
        book.setName("c");
        book.setDescription("c");
        bookDao.updateById(book);
    }

    @Test
    void testDelete() {
        bookDao.deleteById(17);
    }

    @Test
    void testGetAll() {
        bookDao.selectList(null);
    }

    @Test
    void testGetPage() {
        IPage page = new Page(1,2);
        bookDao.selectPage(page,null);
        System.out.println(page.getPages());   //   一共查出来多少页  给了 pages
        System.out.println(page.getCurrent());   //  当前页就是起始页   page写的current
        System.out.println(page.getRecords()); //   查询出来的结果
        System.out.println(page.getSize());  //   就是设置的末页  page里的size
        System.out.println(page.getTotal());  //   查询的所有数据 给了 total
    }


    // 条件查询
    @Test
    void testGetBy() {
        QueryWrapper<Book> qw = new QueryWrapper<>();
        qw.like("name","Spring");   // 有风险，可能写错列名
        bookDao.selectList(qw);
    }

    @Test
    void testGetBy2() {
        String name = "Spring";
        LambdaQueryWrapper<Book> lqw = new LambdaQueryWrapper<>();

//        if (name != null) lqw.like(Book::getName,name);
        lqw.like(name != null,Book::getName,name);

        bookDao.selectList(lqw);
    }
}
