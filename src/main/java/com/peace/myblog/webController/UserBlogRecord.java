package com.peace.myblog.webController;

import com.peace.myblog.response.CommonReturnType;
import com.peace.myblog.service.BlogRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author YR#
 * @create 2020-09-17 20:41
 */
@RestController
@RequestMapping("/user")
public class UserBlogRecord {


    @Autowired
    private BlogRecordService blogRecordService;

    @GetMapping("/listBlogRecord")
    public CommonReturnType listBlogRecord(){
        return CommonReturnType.create(blogRecordService.getAllRecord());
    }

}
