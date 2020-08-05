package com.ustc.mybatis_plus;

import com.ustc.mybatis_plus.entity.User;
import com.ustc.mybatis_plus.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class MybatisPlusApplicationTests {

    @Autowired
    public UserMapper userMapper;

    @Test
    void contextLoads() {
    }

    @Test
    void testSelectList() {
        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::print);
    }




}
