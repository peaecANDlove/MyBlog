package com.peace.myblog.webController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author YR#
 * @create 2021-03-01 17:33
 */
@Controller
public class UserLoginController {

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/register")
    public String register() {
        return "login";
    }
}
