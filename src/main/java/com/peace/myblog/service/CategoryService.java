package com.peace.myblog.service;

import com.peace.myblog.daoObject.Category;
import com.peace.myblog.dto.BlogCategoryInfo;
import com.peace.myblog.dto.CategoryList;

import java.util.List;

/**
 * @author YR#
 * @create 2020-08-14 13:09
 */
public interface CategoryService {

    /**
     * 保存一个分类
     * @param category
     * @return
     */
    Category saveCategory(Category category);


    /**
     * 根据 id 获取一个分类
     * @param categoryId
     * @return
     */
    Category getCategory(Long categoryId);

    /**
     * 根据 id 更新一个分类
     * @param category
     * @return
     */
    Category updateCategory(Category category);

    /**
     * 根据 id 删除一个分类
     * @param categoryId
     */
    void deleteCategory(Long categoryId);

    /**
     * 获取所有分类，用于分页展示
     * @return
     */
    List<Category> getAllCategory();

    /**
     * 根据名称获取分类
     * @param name
     * @return
     */
    Category getCategoryByName(String name);

    /**
     * 分类页类目列表展示
     * @return
     */
    List<CategoryList> categoryList();

    /**
     * 分类页全部分类展示
     * @return
     */
    List<BlogCategoryInfo> blogCategoryInfoList();

}
