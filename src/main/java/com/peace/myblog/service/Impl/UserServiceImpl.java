package com.peace.myblog.service.Impl;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.peace.myblog.daoObject.User;
import com.peace.myblog.mapper.UserMapper;
import com.peace.myblog.service.UserService;
import com.peace.myblog.utils.RedisUtil;
import com.peace.myblog.utils.SmsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author YR#
 * @create 2020-08-12 20:02
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private BCryptPasswordEncoder encoder;

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

    @Override
    public int isUserExist(long telephone) {
        return userMapper.isUserExist(Long.toString(telephone));
    }

    @Override
    public int getTelephoneCode(String telephone) {
        int userExit = userMapper.isUserExist(telephone);
        int code = (int)(Math.random()*9000)+1000;
        int flag = 1;

        if (userExit != 0) {
            flag = 0;
            return flag;
        }

        try {
            SendSmsResponse sendSmsResponse = SmsUtils.sendSms(telephone, Integer.toString(code));
            RedisUtil.putValue(telephone, Integer.toString(code), 300);
        } catch (ClientException e) {
            flag = 0;
            e.printStackTrace();
        }

        return flag;
    }

    @Override
    public int register(User user, String telephoneCode) {
        if (RedisUtil.hasKey(user.getAccountNumber()) == true) {
            String code = RedisUtil.getValue(user.getAccountNumber());
            if (code.equals(telephoneCode) == true) {
                user.setRole(2);
                user.setCreateTime(new Date());
                user.setUpdateTime(new Date());
                user.setAvatar("sss");
                return 1;
            }
        }
        return 0;
    }

    @Override
    public String getAvatar(String name) {
        return userMapper.getAvatar(name);
    }

    @Override
    public int resetPassword(User user, String telephoneCode) {
        if (RedisUtil.hasKey(user.getAccountNumber()) == true) {
            String code = RedisUtil.getValue(user.getAccountNumber());
            if (code.equals(telephoneCode) == true) {
                user.setPassword(encoder.encode(user.getPassword()));
                userMapper.updatePassword(user);
                return 1;
            }
        }
        return 0;
    }


}
