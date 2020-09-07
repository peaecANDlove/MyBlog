package com.peace.myblog.service.Impl;

import com.peace.myblog.mapper.CommentMapper;
import com.peace.myblog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author YR#
 * @create 2020-08-25 16:51
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public Long countAllComment() {
        return commentMapper.countComment();
    }
}
