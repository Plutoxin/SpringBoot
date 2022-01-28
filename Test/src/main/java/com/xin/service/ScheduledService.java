package com.xin.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ScheduledService {

    // 在一个特定的时间执行这个方法~   Timer

    // cron表达式
    // 秒  分  时  日  月  周几~
    @Scheduled(cron = "0/3 * * * * ?")   // ?每一天
    public void hello() {
        System.out.println("你被执行了！！");
    }

}
