package com.peace.myblog.webController.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.peace.myblog.daoObject.Category;
import com.peace.myblog.service.CategoryService;
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
 * @create 2020-08-14 14:14
 */

@Controller
@RequestMapping("/admin")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;



    /**
     * 展示所有分类
     * @param pageNum
     * @param size
     * @param model
     * @return
     */
    @GetMapping("/categorys")
    public String listCategory(@RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum,
                               @RequestParam(value = "size", defaultValue = "10") Integer size,
                               Model model) {

        String orderByTime = "id desc";
        PageHelper.startPage(pageNum, size, orderByTime);
        List<Category> categories = categoryService.getAllCategory();
        PageInfo<Category> pageInfo = new PageInfo<>(categories);
        model.addAttribute("pageInfo", pageInfo);
        return "admin/categorys";

    }


    /**
     * 保存一个分类
     * @param category
     * @param attributes
     * @param result
     * @return
     */
    @PostMapping("/categorys")
    public String saveCategory(@Valid Category category,
                               RedirectAttributes attributes,
                               BindingResult result) {
        Category validCategory = categoryService.getCategoryByName(category.getName());
        if (validCategory != null) {
            result.rejectValue("name", "nameError", "已存在该分类名");
        }

        if (result.hasErrors()) {
            return "admin/category-input";
        }

        Category returnCategory =  categoryService.saveCategory(category);
       if (returnCategory == null) {
            attributes.addFlashAttribute("message", "操作失败");

       } else {
            attributes.addFlashAttribute("message", "操作成功");

       }

       return "redirect:/admin/categorys";
    }

    /**
     * 更新方法
     * @param category
     * @param result
     * @param attributes
     * @param id
     * @return
     */
    @PostMapping("/category/{id}")
    public String updateCategory(@Valid Category category,
                                 BindingResult result,
                                 RedirectAttributes attributes,
                                 @PathVariable Long id) {
        Category validCategory = categoryService.getCategoryByName(category.getName());
        if (validCategory != null) {
            result.rejectValue("name", "nameError", "不能添加重复的类");
        }

        if (result.hasErrors()) {
            return "admin/category-input";
        }

        Category returnCategory =  categoryService.updateCategory(category);
        if (returnCategory == null) {
            attributes.addFlashAttribute("message", "操作失败");

        } else {
            attributes.addFlashAttribute("message", "操作成功");

        }

        return "redirect:/admin/categorys";
    }

    @GetMapping("/category/{id}/delete")
    public String deleteCategory(@PathVariable Long id,
                                 RedirectAttributes attributes) {
        categoryService.deleteCategory(id);
        attributes.addFlashAttribute("message", "删除成功");
        return "redirect:/admin/categorys";
    }

    @GetMapping("/category/input")
    public String input(Model model) {
        model.addAttribute("category", new Category());
        return "admin/category-input";
    }

    @GetMapping("/category/{id}/edit")
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("category", categoryService.getCategory(id));
        return "admin/category-input";
    }



}
