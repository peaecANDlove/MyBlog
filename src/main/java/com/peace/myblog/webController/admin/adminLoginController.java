package com.peace.myblog.webController.admin;

import com.peace.myblog.daoObject.User;
import com.peace.myblog.error.MeForbiddenException;
import com.peace.myblog.dto.UserModel;
import com.peace.myblog.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

/**
 * @author YR#
 * @create 2020-08-13 23:33
 */

@Controller
@RequestMapping("/admin")
public class adminLoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder encoder;


    @GetMapping
    public String adminLogin() {
        return "admin/adminLogin";
    }


    @GetMapping("/blog")
    public String adminIndex() {
        return "admin/blogs";
    }


    @PostMapping("/login")
    public String adminLogin(@RequestParam("accountNumber") String accountNumber,
                             @RequestParam("password") String password,
                             HttpSession session,
                             RedirectAttributes attributes)  {

        User user = userService.getUserByAccountNumber(accountNumber);

        if (user.getRole() == 2) {

            throw new MeForbiddenException("请求被拒绝，你没有权限访问改资源");
        }


        if (user != null && encoder.matches(password, user.getPassword())) {
            UserModel userModel = new UserModel();
            BeanUtils.copyProperties(user, userModel);
            session.setAttribute("userModel", userModel);
            return "admin/firstPage";

        } else {

            attributes.addFlashAttribute("message", "用户名密码错误");
            return "redirect:/admin";
        }



    }


    @GetMapping("/logout")
    public String adminLogout(HttpSession session) {
        session.removeAttribute("userModel");
        return "redirect:/admin";
    }


    @GetMapping("/input")
    public String input() {
        return "admin/blogs-input";
    }
}
