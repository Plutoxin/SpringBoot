package com.xin;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xin.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
class Redis02SpringbootApplicationTests {

    @Autowired
    @Qualifier("redisTemplate") // 指定类名
    private RedisTemplate redisTemplate;


    @Test
    void contextLoads() {

        //  redisTemplate   操作不同的数据类型，api和我们的指令是一样的
        //  opsForValue  操作字符串  类似String
        //  opsForList   操作List  类似 List
        //  opsForSet
        //  opsForHash
        //  opsForGeo
        //  opsForZSet
        //  opsForHyperLogLog

        // 除了基本的操作，我们常用的方法都可以直接通过redisTemplate操作，比如事务，和基本的crud

        // 获取redis的连接对象
//        RedisConnection connection = redisTemplate.getConnectionFactory().getConnection();
//        connection.flushAll();
//        connection.flushDb();
        redisTemplate.opsForValue().set("mykey","鑫java");
        System.out.println(redisTemplate.opsForValue().get("mykey"));
    }

    @Test
    void test() throws JsonProcessingException {
        // 真实的开发一般都适用json来传递对象
        User user = new User("鑫", 19);
        // 序列化  如果不序列化 会报错，所有对象需要序列化
//        String json = new ObjectMapper().writeValueAsString(user);
        redisTemplate.opsForValue().set("user",user);
        System.out.println(redisTemplate.opsForValue().get("user"));
    }
}
