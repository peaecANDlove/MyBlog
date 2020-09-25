package com.peace.myblog.webController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.peace.myblog.dto.BlogCategoryInfo;
import com.peace.myblog.dto.CategoryList;
import com.peace.myblog.dto.CategoryReturn;
import com.peace.myblog.dto.MyPageInfo;
import com.peace.myblog.response.CommonReturnType;
import com.peace.myblog.service.BlogService;
import com.peace.myblog.service.CategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/categoryBlogMergeList")
    public CommonReturnType categoryBlogMergeList(@RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum,
                                                  @RequestParam(value = "size", defaultValue = "3") Integer size) {



        String orderBy = "create_time desc";
        PageHelper.startPage(pageNum, size, orderBy);
        List<BlogCategoryInfo> blogCategoryInfos = categoryService.blogCategoryInfoList();
        PageInfo<BlogCategoryInfo> pageInfo = new PageInfo<>(blogCategoryInfos);
        for (BlogCategoryInfo blogCategoryInfo: blogCategoryInfos) {
            blogCategoryInfo.setCategoryName((categoryService.getCategory(blogCategoryInfo.getCategoryId())).getName());
        }

        MyPageInfo myPageInfo = new MyPageInfo();

        CategoryReturn categoryReturn = new CategoryReturn();
        BeanUtils.copyProperties(pageInfo, myPageInfo);
        categoryReturn.setBlogCategoryInfos(blogCategoryInfos);
        categoryReturn.setMyPageInfo(myPageInfo);


        return CommonReturnType.create(categoryReturn);
    }

    @PostMapping("/listCategoryBlogMergeByTime/{createTime}")
    public CommonReturnType listCategoryBlogMergeByTime(@RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum,
                                                        @RequestParam(value = "size", defaultValue = "3") Integer size,
                                                        @PathVariable("createTime") String createTime){

        String[] time = createTime.split("-");
        String publishDate = time[0]+"年"+time[1]+"月";
        System.out.println(publishDate);
        String orderBy = "create_time desc";
        PageHelper.startPage(pageNum, size, orderBy);
        List<BlogCategoryInfo> blogCategoryInfos = categoryService.listBlogCategoryByTime(publishDate);
        PageInfo<BlogCategoryInfo> pageInfo = new PageInfo<>(blogCategoryInfos);
        for (BlogCategoryInfo blogCategoryInfo: blogCategoryInfos) {
            blogCategoryInfo.setCategoryName((categoryService.getCategory(blogCategoryInfo.getCategoryId())).getName());
        }

        MyPageInfo myPageInfo = new MyPageInfo();

        CategoryReturn categoryReturn = new CategoryReturn();
        BeanUtils.copyProperties(pageInfo, myPageInfo);
        categoryReturn.setBlogCategoryInfos(blogCategoryInfos);
        categoryReturn.setMyPageInfo(myPageInfo);

        return CommonReturnType.create(categoryReturn);
    }

    @PostMapping("/listCategoryBlogMergeByName/{categoryName}")
    public CommonReturnType listCategoryBlogMergeByName(@RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum,
                                                        @RequestParam(value = "size", defaultValue = "3") Integer size,
                                                        @PathVariable("categoryName") String categoryName) {
        String orderBy = "create_time desc";
        PageHelper.startPage(pageNum, size, orderBy);
        List<BlogCategoryInfo> blogCategoryInfos = categoryService.listCategoryByCategoryName(categoryName);
        PageInfo<BlogCategoryInfo> pageInfo = new PageInfo<>(blogCategoryInfos);
        for (BlogCategoryInfo blogCategoryInfo: blogCategoryInfos) {
            blogCategoryInfo.setCategoryName((categoryService.getCategory(blogCategoryInfo.getCategoryId())).getName());
        }
        MyPageInfo myPageInfo = new MyPageInfo();
        CategoryReturn categoryReturn = new CategoryReturn();
        BeanUtils.copyProperties(pageInfo, myPageInfo);
        categoryReturn.setBlogCategoryInfos(blogCategoryInfos);
        categoryReturn.setMyPageInfo(myPageInfo);
        return CommonReturnType.create(categoryReturn);

    }
}
