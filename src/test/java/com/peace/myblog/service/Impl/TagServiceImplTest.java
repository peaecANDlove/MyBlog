package com.peace.myblog.service.Impl;

import com.peace.myblog.service.TagService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

/**
 * @author YR#
 * @create 2020-08-17 13:28
 */
@SpringBootTest
class TagServiceImplTest {

    @Autowired
    TagService tagService;

    @Test
    void getTagByBlog() {
        List<Long> ids =  Arrays.asList(new Long(5), new Long(6), new Long(7));
        System.out.println(tagService.getTagByBlog(ids));

    }
}