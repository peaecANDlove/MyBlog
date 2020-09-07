package com.peace.myblog.webController.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.peace.myblog.daoObject.BlogRecord;
import com.peace.myblog.service.BlogRecordService;
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
 * @create 2020-08-19 12:25
 */
@Controller
@RequestMapping("/admin")
public class BlogRecordController {


    @Autowired
    private BlogRecordService blogRecordService;

    /**
     * 展示所有记录
     * @param pageNum
     * @param size
     * @param model
     * @return
     */
    @GetMapping("/blogRecords")
    public String listRecord(@RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum,
                               @RequestParam(value = "size", defaultValue = "3") Integer size,
                               Model model) {

        String orderByTime = "id desc";
        PageHelper.startPage(pageNum, size, orderByTime);
        List<BlogRecord> record = blogRecordService.getAllRecord();
        PageInfo<BlogRecord> pageInfo = new PageInfo<>(record);
        model.addAttribute("pageInfo", pageInfo);
        return "admin/blogRecords";

    }


    /**
     * 保存一个记录
     * @param blogRecord
     * @param attributes
     * @param result
     * @return
     */
    @PostMapping("/blogRecords")
    public String saveRecord(@Valid BlogRecord blogRecord,
                               RedirectAttributes attributes,
                               BindingResult result) {
        BlogRecord validBlogRecord = blogRecordService.getRecordByName(blogRecord.getRecordWord());
        if (validBlogRecord != null) {
            result.rejectValue("name", "nameError", "已存在该博客记录");
        }

        if (result.hasErrors()) {
            return "admin/blogRecord-input";
        }

        BlogRecord returnBlogRecord =  blogRecordService.saveRecord(blogRecord);
        if (returnBlogRecord == null) {
            attributes.addFlashAttribute("message", "操作失败");

        } else {
            attributes.addFlashAttribute("message", "操作成功");

        }

        return "redirect:/admin/blogRecords";
    }

    /**
     * 更新方法
     * @param blogRecord
     * @param result
     * @param attributes
     * @param id
     * @return
     */
    @PostMapping("/blogRecords/{id}")
    public String update(@Valid BlogRecord blogRecord,
                                 BindingResult result,
                                 RedirectAttributes attributes,
                                 @PathVariable Long id) {
        BlogRecord validRecord = blogRecordService.getRecordByName(blogRecord.getRecordWord());
        if (validRecord != null) {
            result.rejectValue("name", "nameError", "不能添加重复的记录");
        }

        if (result.hasErrors()) {
            return "admin/blogRecord-input";
        }

        BlogRecord returnBlogRecord =  blogRecordService.updateRecord(blogRecord);
        if (returnBlogRecord == null) {
            attributes.addFlashAttribute("message", "操作失败");

        } else {
            attributes.addFlashAttribute("message", "操作成功");

        }

        return "redirect:/admin/blogRecords";
    }

    @GetMapping("/blogRecord/{id}/delete")
    public String deleteRecord(@PathVariable Long id,
                                 RedirectAttributes attributes) {
        blogRecordService.deleteRecord(id);
        attributes.addFlashAttribute("message", "删除成功");
        return "redirect:/admin/blogRecords";
    }

    @GetMapping("/blogRecord/input")
    public String input(Model model) {
        model.addAttribute("blogRecord", new BlogRecord());
        return "admin/blogRecord-input";
    }

    @GetMapping("/blogRecord/{id}/edit")
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("blogRecord", blogRecordService.getRecord(id));
        return "admin/blogRecord-input";
    }
}
