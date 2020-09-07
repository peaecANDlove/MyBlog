package com.peace.myblog.service.Impl;

import com.peace.myblog.daoObject.User;
import com.peace.myblog.mapper.UserMapper;
import com.peace.myblog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author YR#
 * @create 2020-08-12 20:02
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUserByAccountNumber(String accountNumber) {

       return userMapper.getUserByAccountNumber(accountNumber);

    }


    @Override
    @Transactional
    public Long createUser(User user) {

        return userMapper.createUser(user);
    }

    @Override
    @Transactional
    public List<User> getAllUser() {
        return userMapper.getAllUser();
    }

    @Override
    public void deleteUser(Long userId) {
        userMapper.deleteUser(userId);
    }

    @Override
    public User getUsernameAndId(Long userId) {
        return userMapper.getUsernameAndId(userId);
    }


}
