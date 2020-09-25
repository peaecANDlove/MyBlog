package com.peace.myblog.service;

import com.peace.myblog.daoObject.Blog;
import com.peace.myblog.dto.BlogCategoryInfo;
import com.peace.myblog.dto.BlogModel;
import com.peace.myblog.dto.BlogSeach;
import com.peace.myblog.dto.WebsiteInfo;

import java.util.List;

/**
 * @author YR#
 * @create 2020-08-16 16:26
 */
public interface BlogService {

    Blog getBlog(Long id);

    BlogModel getBlogModel(Long id);

    List<Blog> getAllBlog(BlogSeach blog);

    Blog saveBlog(BlogModel blog);

    Blog updateBlog(Long id, Blog blog);

    void deleteBlog(Long id);

    List<Blog> getAllBlog();

    List<Blog> recommendBlog();

    WebsiteInfo getAllInfo();

    Integer getNumberOfBlogByCategoryId(Long categoryId);

    List<BlogCategoryInfo> getAllBlogCategoryMerge();

    Integer getBlogsByArchive(String archiveName);

    List<BlogCategoryInfo> getBlogByPublishDate(String publishDate);

    List<BlogCategoryInfo> getBlogByCategory(Long categoryId);

}
