package com.peace.myblog.webController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.peace.myblog.dto.BlogCategoryInfo;
import com.peace.myblog.dto.CategoryReturn;
import com.peace.myblog.dto.MyPageInfo;
import com.peace.myblog.response.CommonReturnType;
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
 * @create 2020-09-13 19:16
 */
@RestController
@RequestMapping("/user")
public class UserTimelineController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/timelineCards")
    public CommonReturnType timelineCards(@RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum,
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
}
