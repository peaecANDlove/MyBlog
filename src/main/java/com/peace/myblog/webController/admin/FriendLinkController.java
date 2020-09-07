package com.peace.myblog.webController.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.peace.myblog.daoObject.FriendLink;
import com.peace.myblog.service.FriendLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

/**
 * @author YR#
 * @create 2020-08-20 17:34
 */
@Controller
@RequestMapping("/admin")
public class FriendLinkController {

    @Autowired
    private FriendLinkService friendLinkService;

    /**
     * 展示所有记录
     * @param pageNum
     * @param size
     * @param model
     * @return
     */
    @GetMapping("/friendLinks")
    public String listFriendLink(@RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum,
                             @RequestParam(value = "size", defaultValue = "3") Integer size,
                             Model model) {

        String orderByTime = "id desc";
        PageHelper.startPage(pageNum, size, orderByTime);
        List<FriendLink> friendLinks = friendLinkService.getAllFriendLink();
        PageInfo<FriendLink> pageInfo = new PageInfo<>(friendLinks);
        model.addAttribute("pageInfo", pageInfo);
        return "admin/friendLinks";

    }


    /**
     * 保存一个记录
     * @param friendLink
     * @param attributes
     * @param result
     * @return
     */
    @PostMapping("/friendLinks")
    public String saveFriendLink(@Valid FriendLink friendLink,
                             RedirectAttributes attributes,
                             BindingResult result) {
        FriendLink validFriendLink = friendLinkService.getFriendLinkByName(friendLink.getBlogName());
        if (validFriendLink != null) {
            result.rejectValue("name", "nameError", "已存在该友链");
        }

        if (result.hasErrors()) {
            return "admin/friendLink-input";
        }

        FriendLink returnFriendLink =  friendLinkService.saveFriendLink(friendLink);
        if (returnFriendLink == null) {
            attributes.addFlashAttribute("message", "操作失败");

        } else {
            attributes.addFlashAttribute("message", "操作成功");

        }

        return "redirect:/admin/friendLinks";
    }

    /**
     * 更新方法
     * @param friendLink
     * @param result
     * @param attributes
     * @param id
     * @return
     */
    @PostMapping("/friendLink/{id}")
    public String update(@Valid FriendLink friendLink,
                         BindingResult result,
                         RedirectAttributes attributes,
                         @PathVariable Long id) {
        FriendLink validFriendLink = friendLinkService.getFriendLinkByName(friendLink.getBlogName());
        if (validFriendLink != null) {
            result.rejectValue("name", "nameError", "不能添加重复的友链");
        }

        if (result.hasErrors()) {
            return "admin/friendLink-input";
        }

        FriendLink returnFriendLink =  friendLinkService.updateFriendLink(friendLink);
        if (returnFriendLink == null) {
            attributes.addFlashAttribute("message", "操作失败");

        } else {
            attributes.addFlashAttribute("message", "操作成功");

        }

        return "redirect:/admin/friendLinks";
    }

    @GetMapping("/friendLink/{id}/delete")
    public String deleteFriendLink(@PathVariable Long id,
                               RedirectAttributes attributes) {
        friendLinkService.deleteFriendLink(id);
        attributes.addFlashAttribute("message", "删除成功");
        return "redirect:/admin/friendLinks";
    }

    @GetMapping("/friendLink/input")
    public String input(Model model) {
        model.addAttribute("friendLink", new FriendLink());
        return "admin/friendLink-input";
    }

    @GetMapping("/friendLink/{id}/edit")
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("friendLink", friendLinkService.getFriendLink(id));
        return "admin/friendLink-input";
    }
}
