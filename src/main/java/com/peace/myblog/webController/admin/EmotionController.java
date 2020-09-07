package com.peace.myblog.webController.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.peace.myblog.daoObject.EmotionMe;
import com.peace.myblog.service.EmotionService;
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
 * @create 2020-08-19 13:19
 */
@Controller
@RequestMapping("/admin")
public class EmotionController {


    @Autowired
    private EmotionService emotionService;

    /**
     * 展示所有记录
     * @param pageNum
     * @param size
     * @param model
     * @return
     */
    @GetMapping("/emotions")
    public String listRecord(@RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum,
                             @RequestParam(value = "size", defaultValue = "3") Integer size,
                             Model model) {

        String orderByTime = "id desc";
        PageHelper.startPage(pageNum, size, orderByTime);
        List<EmotionMe> emotion = emotionService.getAllEmotion();
        PageInfo<EmotionMe> pageInfo = new PageInfo<>(emotion);
        model.addAttribute("pageInfo", pageInfo);
        return "admin/meEmotions";

    }


    /**
     * 保存一个记录
     * @param emotionMe
     * @param attributes
     * @param result
     * @return
     */
    @PostMapping("/emotions")
    public String saveRecord(@Valid EmotionMe emotionMe,
                             RedirectAttributes attributes,
                             BindingResult result) {
        EmotionMe validEmotion = emotionService.getEmotionByName(emotionMe.getEmotionWord());
        if (validEmotion != null) {
            result.rejectValue("name", "nameError", "已存在该心情");
        }

        if (result.hasErrors()) {
            return "admin/meEmotion-input";
        }

        EmotionMe returnEmotion =  emotionService.saveEmotion(emotionMe);
        if (returnEmotion == null) {
            attributes.addFlashAttribute("message", "操作失败");

        } else {
            attributes.addFlashAttribute("message", "操作成功");

        }

        return "redirect:/admin/emotions";
    }

    /**
     * 更新方法
     * @param emotionMe
     * @param result
     * @param attributes
     * @param id
     * @return
     */
    @PostMapping("/emotion/{id}")
    public String update(@Valid EmotionMe emotionMe,
                         BindingResult result,
                         RedirectAttributes attributes,
                         @PathVariable Long id) {
        EmotionMe validEmotion = emotionService.getEmotionByName(emotionMe.getEmotionWord());
        if (validEmotion != null) {
            result.rejectValue("name", "nameError", "不能添加重复的心情");
        }

        if (result.hasErrors()) {
            return "admin/meEmotion-input";
        }

        EmotionMe returnEmotion =  emotionService.updateEmotion(emotionMe);
        if (returnEmotion == null) {
            attributes.addFlashAttribute("message", "操作失败");

        } else {
            attributes.addFlashAttribute("message", "操作成功");

        }

        return "redirect:/admin/emotions";
    }

    @GetMapping("/emotion/{id}/delete")
    public String deleteRecord(@PathVariable Long id,
                               RedirectAttributes attributes) {
        emotionService.deleteEmotion(id);
        attributes.addFlashAttribute("message", "删除成功");
        return "redirect:/admin/emotions";
    }

    @GetMapping("/emotion/input")
    public String input(Model model) {
        model.addAttribute("emotion", new EmotionMe());
        return "admin/meEmotion-input";
    }

    @GetMapping("/emotion/{id}/edit")
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("emotion", emotionService.getEmotion(id));
        return "admin/meEmotion-input";
    }
}
