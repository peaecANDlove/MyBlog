package com.peace.myblog.webController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.peace.myblog.daoObject.Blog;
import com.peace.myblog.dto.BlogCategoryInfo;
import com.peace.myblog.dto.CategoryList;
import com.peace.myblog.dto.CategoryReturn;
import com.peace.myblog.dto.MyPageInfo;
import com.peace.myblog.response.CommonReturnType;
import com.peace.myblog.service.BlogService;
import com.peace.myblog.service.CategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author YR#
 * @create 2020-09-07 19:47
 */
@RestController
@RequestMapping("/user")
public class UserCategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private BlogService blogService;

    @GetMapping("/categoryList")
    public CommonReturnType categoryList(){

        List<CategoryList> blogCategoryInfos = categoryService.categoryList();

        return CommonReturnType.create(blogCategoryInfos);
    }

    @GetMapping("/categoryBlogMergeList")
    public CommonReturnType categoryBlogMergeList(@RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum,
                                                  @RequestParam(value = "size", defaultValue = "3") Integer size) {

        String orderBy = "id desc";
        PageHelper.startPage(pageNum, size, orderBy);
        List<Blog> blogs = blogService.getAllBlog();
        PageInfo<Blog> pageInfo = new PageInfo<>(blogs);
        MyPageInfo myPageInfo = new MyPageInfo();
        BeanUtils.copyProperties(pageInfo, myPageInfo);
        List<BlogCategoryInfo> blogCategoryInfos = categoryService.blogCategoryInfoList();
        CategoryReturn categoryReturn = new CategoryReturn();
        categoryReturn.setBlogCategoryInfos(blogCategoryInfos);
        categoryReturn.setMyPageInfo(myPageInfo);


        return CommonReturnType.create(categoryReturn);
    }
}
