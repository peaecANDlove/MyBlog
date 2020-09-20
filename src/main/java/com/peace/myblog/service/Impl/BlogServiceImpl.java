package com.peace.myblog.service.Impl;

import com.peace.myblog.daoObject.Blog;
import com.peace.myblog.daoObject.Category;
import com.peace.myblog.daoObject.Tag;
import com.peace.myblog.daoObject.User;
import com.peace.myblog.dto.BlogCategoryInfo;
import com.peace.myblog.dto.BlogModel;
import com.peace.myblog.dto.BlogSeach;
import com.peace.myblog.dto.WebsiteInfo;
import com.peace.myblog.error.MeNotFoundException;
import com.peace.myblog.mapper.*;
import com.peace.myblog.service.BlogService;
import com.peace.myblog.service.UserService;
import com.peace.myblog.utils.StringBothConvertLongArray;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author YR#
 * @create 2020-08-16 16:28
 */
@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogMapper blogMapper;

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private TagsMapper tagsMapper;

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private BlogRecordMapper blogRecordMapper;

    @Autowired
    private UserService userService;


    @Override
    @Transactional
    public Blog getBlog(Long id) {
        return blogMapper.getBlogById(id);
    }



    @Override
    @Transactional
    public BlogModel getBlogModel(Long id) {
        Blog blog = getBlog(id);
        BlogModel returnBlogModel = new BlogModel();
        Category category = categoryMapper.getCategory(blog.getCategoryId());
        User user = userService.getUsernameAndId(blog.getAuthorId());
        BeanUtils.copyProperties(blog, returnBlogModel);
        if (blog.getTagNames() != null) {
            List<Tag> tags = tagsMapper.getTagsByBlogTagName(StringBothConvertLongArray.convertToStringList(blog.getTagNames()));
            returnBlogModel.setTags(tags);
            returnBlogModel.setTagNames(blog.getTagNames());
        }
        returnBlogModel.setCategory(category);
        returnBlogModel.setAuthorName(user.getNickName());

        return returnBlogModel;
    }

    @Override
    @Transactional
    public List<Blog> getAllBlog(BlogSeach blog) {

        return blogMapper.getAllBlog(blog);
    }

    @Override
    @Transactional
    public Blog saveBlog(BlogModel blogModel) {

        Blog blog = new Blog();
        List<String> tagNames = StringBothConvertLongArray.convertToStringList(blogModel.getTagNames());
        for (String s: tagNames) {
            if (tagsMapper.getTagByName(s) == null) {
                Tag tag = new Tag();
                tag.setCreateTime(new Date());
                tag.setTagName(s);
                tagsMapper.saveTag(tag);
            }
        }

        if (blogModel.getId() == null) {
            blogModel.setCreateTime(new Date());
            blogModel.setUpdateTime(new Date());

            SimpleDateFormat tempDate = new SimpleDateFormat("yyyy年MM月");
            String publishDate = tempDate.format(new Date());
            blogModel.setPublishDate(publishDate);
            blogModel.setViews(0);

            BeanUtils.copyProperties(blogModel, blog);
            blog.setTagNames(blogModel.getTagNames());
            blog.setCategoryId(blogModel.getCategory().getId());
            blogMapper.saveBlog(blog);

        } else {
            blogModel.setUpdateTime(new Date());
            BeanUtils.copyProperties(blogModel, blog);
            blog.setTagNames(blogModel.getTagNames());
            blog.setCategoryId(blogModel.getCategory().getId());
            blogMapper.updateBlog(blog);
        }


        return  blogMapper.getBlogById(blog.getId());
    }

    @Override
    @Transactional
    public Blog updateBlog(Long id, Blog blog) {
        Blog oldBlog = blogMapper.getBlogById(id);
        if (oldBlog == null) {
            throw new MeNotFoundException("博客不存在");
        }

        BeanUtils.copyProperties(blog, oldBlog);
        blogMapper.updateBlog(blog);
        return blogMapper.getBlogById(blog.getId());
    }

    @Override
    @Transactional
    public void deleteBlog(Long id) {
        blogMapper.deleteBlogById(id);
    }

    @Override
    @Transactional
    public List<Blog> getAllBlog() {
        return blogMapper.getAllBlogSimple();
    }

    @Override
    public List<Blog> recommendBlog() {
        return blogMapper.recommendBlog();
    }

    @Override
    @Transactional
    public WebsiteInfo getAllInfo() {
        WebsiteInfo websiteInfo = new WebsiteInfo();
        Long totalBlog = blogMapper.countAllBlog();
        Long totalCategory = categoryMapper.countAllCategory();
        Long totalTag = tagsMapper.countAllTag();
        Long totalComment = commentMapper.countComment();
        Date updateTime = blogRecordMapper.getBlogRecordTime();
        websiteInfo.setTotalBlog(totalBlog != null ? totalBlog : 0L);
        websiteInfo.setTotalCategory(totalCategory != null ? totalCategory : 0L);
        websiteInfo.setTotalTag(totalTag != null ? totalTag : 0L);
        websiteInfo.setTotalComment(totalComment != null ? totalComment : 0L);
        websiteInfo.setWebsiteUpdateTime(updateTime != null ? updateTime : new Date());
        return websiteInfo;
    }

    @Override
    public Integer getNumberOfBlogByCategoryId(Long categoryId) {
        return blogMapper.getNumberOfBlogByCategoeyId(categoryId);
    }

    @Override
    public List<BlogCategoryInfo> getAllBlogCategoryMerge() {
        return blogMapper.getBlogCategoryMerge();
    }

    @Override
    public Integer getBlogsByArchive(String archiveName) {
        return blogMapper.getArchiveNumber(archiveName);
    }
}
