package com.peace.myblog.service.Impl;

import com.peace.myblog.service.CommentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author YR#
 * @create 2020-08-25 17:21
 */
@SpringBootTest
class CommentServiceImplTest {

    @Autowired
    private CommentService commentService;


    @Test
    void countAllComment() {
        System.out.println(commentService.countAllComment());

    }
}