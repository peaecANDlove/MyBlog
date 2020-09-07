package com.peace.myblog.webController.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.peace.myblog.daoObject.Tag;
import com.peace.myblog.service.TagService;
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
 * @create 2020-08-16 13:14
 */
@Controller
@RequestMapping("/admin")
public class TagController {

    @Autowired
    private TagService tagService;

    /**
     * 展示所有标签
     * @param pageNum
     * @param size
     * @param model
     * @return
     */
    @GetMapping("/tags")
    public String listCategory(@RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum,
                               @RequestParam(value = "size", defaultValue = "10") Integer size,
                               Model model) {

        String orderByTime = "id desc";
        PageHelper.startPage(pageNum, size, orderByTime);
        List<Tag> tags = tagService.getAllTag();
        PageInfo<Tag> pageInfo = new PageInfo<>(tags);
        model.addAttribute("pageInfo", pageInfo);
        return "admin/tags";

    }


    /**
     * 保存一个标签
     * @param tag
     * @param attributes
     * @param result
     * @return
     */
    @PostMapping("/tags")
    public String saveCategory(@Valid Tag tag,
                               RedirectAttributes attributes,
                               BindingResult result) {
        Tag validTag = tagService.getTagByName(tag.getTagName());
        if (validTag != null) {
            result.rejectValue("tagName", "nameError", "已存在该标签名");
        }

        if (result.hasErrors()) {
            return "admin/tag-input";
        }

        Tag returnTag =  tagService.saveTag(tag);
        if (returnTag == null) {
            attributes.addFlashAttribute("message", "新增失败");

        } else {
            attributes.addFlashAttribute("message", "新增成功");

        }

        return "redirect:/admin/tags";
    }

    /**
     * 更新方法
     * @param tag
     * @param result
     * @param attributes
     * @param id
     * @return
     */
    @PostMapping("/tag/{id}")
    public String updateCategory(@Valid Tag tag,
                                 BindingResult result,
                                 RedirectAttributes attributes,
                                 @PathVariable Long id) {
        Tag validTag = tagService.getTagByName(tag.getTagName());
        if (validTag != null) {
            result.rejectValue("tagName", "nameError", "不能修改为已有标签名");
        }

        if (result.hasErrors()) {
            return "tag-input";
        }

        Tag returnCategory =  tagService.updateTag(tag);
        if (returnCategory == null) {
            attributes.addFlashAttribute("message", "更新失败");

        } else {
            attributes.addFlashAttribute("message", "更新成功");

        }

        return "redirect:/admin/listTag";
    }

    @GetMapping("/tag/{id}/delete")
    public String deleteCategory(@PathVariable Long id,
                                 RedirectAttributes attributes) {
        tagService.deleteTag(id);
        attributes.addFlashAttribute("message", "删除成功");
        return "redirect:/admin/tags";
    }

    @GetMapping("/tag/input")
    public String input(Model model) {
        model.addAttribute("tag", new Tag());
        return "admin/tag-input";
    }

    @GetMapping("/tag/{id}/edit")
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("tag", tagService.getTag(id));
        return "admin/tag-input";
    }

}
