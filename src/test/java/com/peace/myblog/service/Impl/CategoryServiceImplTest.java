package com.peace.myblog.service.Impl;

import com.peace.myblog.daoObject.Category;
import com.peace.myblog.service.CategoryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author YR#
 * @create 2020-08-14 16:09
 */
@SpringBootTest
class CategoryServiceImplTest {

    @Autowired
    CategoryService categoryService;

    @Test
    void saveCategory() {
        Category category = new Category();
        category.setName("生活日志");
        System.out.println(categoryService.saveCategory(category));
        System.out.println(category.getId());
    }

    @Test
    void getCategory() {
    }

    @Test
    void updateCategory() {
    }

    @Test
    void deleteCategory() {
    }

    @Test
    void getAllCategory() {
        System.out.println(categoryService.getAllCategory());
    }

    @Test
    void categoryList() {
        System.out.println(categoryService.categoryList());
    }

    @Test
    void categoryBlogMerge() {
        System.out.println(categoryService.blogCategoryInfoList());
    }
}