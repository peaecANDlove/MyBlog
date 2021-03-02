package com.peace.myblog.webController;

import com.peace.myblog.daoObject.User;
import com.peace.myblog.dto.LoginUser;
import com.peace.myblog.response.CommonReturnType;
import com.peace.myblog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * @author YR#
 * @create 2021-03-01 11:41
 */

@RestController
@RequestMapping("user")
public class UserUserController {

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @RequestMapping("/isUserExist")
    public CommonReturnType isUserExist(Long telephone) {
        int flag = userService.isUserExist(telephone);
        if (flag == 1) {
            return CommonReturnType.create(null, "200");

        } else {
           return CommonReturnType.create(null, " 404");
        }
    }

    @RequestMapping("/getUserByProviderId")
    public CommonReturnType getUserPhoneNumber(String telephone) {
        User user = userService.getUserByAccountNumber(telephone);
        if (user == null) {
            return CommonReturnType.create(null, "200");

        } else {
            return CommonReturnType.create(null, "500");
        }

    }

    @PostMapping("/getTelephoneCode")
    public CommonReturnType getTelePhoneCode(String telephone) {
        int flag = userService.getTelephoneCode(telephone);
        if (flag != 1) {
            return CommonReturnType.create(null, "404");
        } else {
            return CommonReturnType.create(null, "200");
        }
    }


    @PostMapping("/register")
    public CommonReturnType register(User user, String telephoneCode) {
        int flag = userService.register(user, telephoneCode);

        if (flag != 1) {
            return  CommonReturnType.create(null, "404");
        } else {
            return CommonReturnType.create(null, "200");
        }
    }


    @PostMapping("/checkUserNameAndPassword")
    public CommonReturnType checkUserNameAndPassword(@RequestParam("telephone") String telephone, @RequestParam("password") String password) {

        User user = userService.getUserByAccountNumber(telephone);
        if (user == null) {
            throw new UsernameNotFoundException("没有当前用户");

        } else {
            if (encoder.matches(password, user.getPassword())) {
                return CommonReturnType.create(null, "1");
            }
        }

        return CommonReturnType.create(null, "0");

    }

    @RequestMapping("/isLogin")
    public CommonReturnType isLogin(@AuthenticationPrincipal Principal principal) {

        LoginUser loginUser = new LoginUser();
        loginUser.setState(0);
        if (principal == null) {
            return CommonReturnType.create(loginUser);
        }

        User user = userService.getUserByAccountNumber(principal.getName());

        loginUser.setAvatar(user.getAvatar());
        loginUser.setId(user.getAccountNumber());
        loginUser.setState(1);
        loginUser.setNickName(user.getNickName());
        return CommonReturnType.create(loginUser);

    }

    @PostMapping("/resetPassword")
    public CommonReturnType resetPassword(User user, String telephone) {

        int flag = userService.resetPassword(user, telephone);
        if (flag == 1) {
            return CommonReturnType.create(null, "200");

        } else {
            return CommonReturnType.create(null, "500");
        }
    }


}
