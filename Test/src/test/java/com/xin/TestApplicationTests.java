package com.xin;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@SpringBootTest
class TestApplicationTests {

    @Autowired
    JavaMailSenderImpl mailSender;


    @Test
    void contextLoads() {

        // 一个简单的邮件
        SimpleMailMessage message = new SimpleMailMessage();

        message.setSubject("你好呀!!");
        message.setText("傻der");

        message.setTo("2206957302@qq.com");
        message.setFrom("1640048467@qq.com");  // 发送的地址

        mailSender.send(message);

    }


    @Test
    void test2() throws MessagingException {

        // 一个复杂的邮件
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        // 组装~~
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true);

        helper.setSubject("啊");
        helper.setText("<p1 style='color:pink'>达咩！！！</p1>",true); // 加了true 之后就支持html标签了

        // 附件
        helper.addAttachment("cg.jpg",new File("C:\\Users\\Pluto\\Desktop\\cg.jpg"));


        helper.setTo("2206957302@qq.com");   // 发送到哪去
        helper.setFrom("1640048467@qq.com");  // 自己的的地址

        mailSender.send(mimeMessage);
    }
    /**
     *
     * @param flag
     * @param subject
     * @param test
     */
    public void sendMail(boolean flag,String subject,String test) throws MessagingException {
        // 一个复杂的邮件
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        // 组装~~
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,flag);

        helper.setSubject(subject);
        helper.setText(test,true); // 加了true 之后就支持html标签了

        // 附件
        helper.addAttachment("cg.jpg",new File("C:\\Users\\Pluto\\Desktop\\cg.jpg"));


        helper.setTo("2206957302@qq.com");   // 发送到哪去
        helper.setFrom("1640048467@qq.com");  // 自己的的地址

        mailSender.send(mimeMessage);
    }
}
