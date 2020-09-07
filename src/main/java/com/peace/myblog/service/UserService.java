package com.peace.myblog.service;

import com.peace.myblog.daoObject.User;

import java.util.List;

/**
 * @author YR#
 * @create 2020-08-12 19:40
 */


public interface UserService {

    /**
     * 获取账号和密码
     * @param accountNumber
     * @return
     */
    User getUserByAccountNumber(String accountNumber);


    /**
     * 创建用户
     * @param user
     * @return
     */
    Long createUser(User user);

    /**
     * 获取所有用户
     * @return
     */
    List<User> getAllUser();

    /**
     * 删除一个用户
     * @param userId
     */
    void deleteUser(Long userId);

    User getUsernameAndId(Long userId);



}
