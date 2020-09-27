package com.peace.myblog.service.Impl;

import com.peace.myblog.daoObject.Category;
import com.peace.myblog.dto.BlogCategoryInfo;
import com.peace.myblog.dto.CategoryList;
import com.peace.myblog.error.MeNotFoundException;
import com.peace.myblog.mapper.CategoryMapper;
import com.peace.myblog.service.BlogService;
import com.peace.myblog.service.CategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author YR#
 * @create 2020-08-14 13:26
 */

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private BlogService blogService;




    @Override
    @Transactional
    public Category saveCategory(Category category) {
        category.setCreateTime(new Date());
        categoryMapper.saveCategory(category);

        return categoryMapper.getCategory(category.getId());
    }

    @Override
    @Transactional
    public Category getCategory(Long categoryId) {
        return categoryMapper.getCategory(categoryId);
    }

    @Override
    @Transactional
    public Category updateCategory(Category category) {
        Category sourceCategory = categoryMapper.getCategory(category.getId());
        if (sourceCategory == null) {
            throw new MeNotFoundException("分类不存在");
        }

        BeanUtils.copyProperties(category, sourceCategory);
        categoryMapper.updateCategory(sourceCategory);
        return getCategory(sourceCategory.getId());

    }

    @Override
    @Transactional
    public void deleteCategory(Long categoryId) {
        categoryMapper.deleteCategory(categoryId);
    }

    @Override
    @Transactional
    public List<Category> getAllCategory() {
        return categoryMapper.getAllCategory();
    }

    @Override
    @Transactional
    public Category getCategoryByName(String name) {
        return categoryMapper.getCategoryByName(name);
    }

    @Override
    public List<CategoryList> categoryList() {

        List<Category> categories = categoryMapper.getAllCategory();
        List<CategoryList> categoryLists = new ArrayList<>();

        for (Category category: categories) {
            CategoryList categoryList = new CategoryList();
            categoryList.setCategoryName(category.getName());
            categoryList.setBlogs(blogService.getNumberOfBlogByCategoryId(category.getId()));
            categoryList.setCategoryId(category.getId());
            if (categoryList.getBlogs() != 0) {
                categoryLists.add(categoryList);
            }
        }

        return categoryLists;
    }

    @Override
    @Transactional
    public List<BlogCategoryInfo> blogCategoryInfoList() {


        return blogService.getAllBlogCategoryMerge();
    }

    @Override
    public List<BlogCategoryInfo> listBlogCategoryByTime(String publishDate) {



        return blogService.getBlogByPublishDate(publishDate);
    }

    @Override
    public List<BlogCategoryInfo> listCategoryByCategoryName(String categoryName) {
        Category category = categoryMapper.getCategoryByName(categoryName);
        return blogService.getBlogByCategory(category.getId());

    }

    @Override
    public List<BlogCategoryInfo> listBlogCategoryByTagName(String categoryName) {
        return blogService.getBlogByTagName(categoryName);
    }

    @Override
    public List<BlogCategoryInfo> blogCategoryInfo(String categoryName) {
        System.out.println(categoryName);
        if (!categoryName.equals("null")) {
            Category categoryId = categoryMapper.getCategoryByName(categoryName);
            return blogService.getAllBlogCategory(categoryId.getId());

        } else {
            return blogService.getAllBlogCategoryMerge();
        }

    }
}
