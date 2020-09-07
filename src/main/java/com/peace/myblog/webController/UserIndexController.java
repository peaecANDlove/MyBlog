package com.peace.myblog.webController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.peace.myblog.daoObject.Blog;
import com.peace.myblog.daoObject.Tag;
import com.peace.myblog.dto.*;
import com.peace.myblog.response.CommonReturnType;
import com.peace.myblog.service.BlogService;
import com.peace.myblog.service.CategoryService;
import com.peace.myblog.service.TagService;
import com.peace.myblog.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author YR#
 * @create 2020-08-21 16:20
 */
@RestController
@RequestMapping("/user")
public class UserIndexController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private UserService userService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private TagService tagService;

    @PostMapping("/articleInfos")
    public CommonReturnType getArticleInfo(@RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum,
                                           @RequestParam(value = "size", defaultValue = "3") Integer size) {
        List<ArticleInfo> articleInfos = new ArrayList<>();
        String orderBy = "id desc";
        PageHelper.startPage(pageNum, size, orderBy);
        List<Blog> blogs = blogService.getAllBlog();
        PageInfo<Blog> pageInfo = new PageInfo<>(blogs);
        MyPageInfo myPageInfo = new MyPageInfo();
        IndexReturn indexReturn = new IndexReturn();

        for (Blog blog: blogs) {
            ArticleInfo articleInfo = new ArticleInfo();
            BeanUtils.copyProperties(blog, articleInfo);
            articleInfo.setAuthorName(userService.getUsernameAndId(blog.getAuthorId()).getNickName());
            articleInfo.setCategory(categoryService.getCategory(blog.getCategoryId()).getName());
            articleInfos.add(articleInfo);
        }

        BeanUtils.copyProperties(pageInfo, myPageInfo);
        indexReturn.setMyPageInfo(myPageInfo);
        indexReturn.setArticleInfos(articleInfos);

        return CommonReturnType.create(indexReturn);
    }


    @PostMapping("/tagCloud")
    public CommonReturnType getTagCloudData() {

        List<Tag> tags = tagService.getAllTag();
        return CommonReturnType.create(tags);
    }

    @GetMapping("/recommendBlog")
    public CommonReturnType recommendBlog() {
        List<RecommendBlog> recommendBlogs = new ArrayList<>();
        List<Blog> blogs = blogService.recommendBlog();

        for (int i=0; i<5; i++) {
            RecommendBlog recommendBlog = new RecommendBlog();
            BeanUtils.copyProperties(blogs.get(i), recommendBlog);
            recommendBlogs.add(recommendBlog);
        }

        return CommonReturnType.create(recommendBlogs);
    }

    @GetMapping("/websiteInfo")
    public CommonReturnType websiteInfo() {
        WebsiteInfo websiteInfo = blogService.getAllInfo();
        return CommonReturnType.create(websiteInfo);
    }


}
