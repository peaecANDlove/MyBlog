package com.peace.myblog.service;

import com.alibaba.fastjson.JSONObject;
import com.peace.myblog.daoObject.Comment;
import org.springframework.stereotype.Service;

/**
 * @author YR#
 * @create 2020-08-25 16:51
 */

@Service
public interface CommentService {

    Long countAllComment();





    /**
     * 添加文章评论
     * @param comment
     * @return
     */
    JSONObject addComment(Comment comment);


    /**
     * 获得文章的评论
     * @param articleId
     * @param userId
     * @return
     */
    JSONObject getCommentsOfArticle(long articleId,long userId);
}
