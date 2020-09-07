package com.peace.myblog.webController.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.peace.myblog.daoObject.User;
import com.peace.myblog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * @author YR#
 * @create 2020-08-21 11:33
 */
@Controller
@RequestMapping("/admin")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 展示所有用户
     * @param pageNum
     * @param size
     * @param model
     * @return
     */
    @GetMapping("/users")
    public String listUser(@RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum,
                           @RequestParam(value = "size", defaultValue = "10") Integer size,
                           Model model) {
        String orderByTime = "id desc";
        PageHelper.startPage(pageNum, size, orderByTime);
        List<User> users = userService.getAllUser();
        PageInfo<User> pageInfo = new PageInfo<>(users);
        model.addAttribute("pageInfo", pageInfo);
        return "admin/users";

    }

    @GetMapping("/user/{id}/delete")
    public String deleteCategory(@PathVariable Long id,
                                 RedirectAttributes attributes) {
        userService.deleteUser(id);
        attributes.addFlashAttribute("message", "删除成功");
        return "redirect:/admin/users";
    }
}
