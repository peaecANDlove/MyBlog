package com.peace.myblog.service.Impl;

import com.peace.myblog.service.BlogService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author YR#
 * @create 2020-08-18 17:12
 */
@SpringBootTest
class BlogServiceImplTest {

    @Autowired
    BlogService blogService;

    @Test
    void getAllBlog() {
        System.out.println(blogService.getAllBlog(null).size());
    }
}