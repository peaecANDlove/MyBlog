package com.peace.myblog.webController;

import com.peace.myblog.dto.BlogModel;
import com.peace.myblog.response.CommonReturnType;
import com.peace.myblog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author YR#
 * @create 2020-09-19 18:34
 */
@RestController
@RequestMapping("/user")
public class UserBlogController {

    @Autowired
    private BlogService blogService;

    @PostMapping("/getBlogDetail/{id}")
    public CommonReturnType getBlogDetail(@PathVariable("id") String id) {
        Long blogId = Long.valueOf(id);
        BlogModel blog = blogService.getBlogModel(blogId);
        return CommonReturnType.create(blog);
    }
}
