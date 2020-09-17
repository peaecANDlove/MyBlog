package com.peace.myblog.service.Impl;

import com.peace.myblog.daoObject.User;
import com.peace.myblog.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Date;

/**
 * @author YR#
 * @create 2020-08-12 20:47
 */
@SpringBootTest
class UserServiceImplTest {

    @Autowired
    UserService userService;

    @Autowired
    BCryptPasswordEncoder encoder;

    @Test
    void createUser() {

        User user = new User();
        user.setAccountNumber("888888");
        user.setPassword(encoder.encode("123456"));
        user.setNickName("admin");
        user.setAvatar("http://www.caafd.com");
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());

        userService.createUser(user);
    }

    void selectAdmin() {

    }
}