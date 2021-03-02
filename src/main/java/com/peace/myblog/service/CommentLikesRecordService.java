package com.peace.myblog.service;

import com.peace.myblog.daoObject.CommentLikesRecord;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author YR#
 * @create 2021-03-02 14:26
 */

@Service
public interface CommentLikesRecordService {

    /**
     * 评论点赞
     * @param commentLikesRecord
     * @return
     */
    int commentLikeAction(CommentLikesRecord commentLikesRecord);

    /**
     * 得到当前用户文章评论点赞记录
     * @param articleId
     * @param userId
     * @return
     */
    List<String> getOwnCommentLikeId(long articleId, long userId);
}
