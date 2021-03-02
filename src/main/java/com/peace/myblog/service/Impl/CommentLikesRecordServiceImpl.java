package com.peace.myblog.service.Impl;

import com.peace.myblog.daoObject.CommentLikesRecord;
import com.peace.myblog.mapper.CommentLikesRecordMapper;
import com.peace.myblog.mapper.CommentMapper;
import com.peace.myblog.service.CommentLikesRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author YR#
 * @create 2021-03-02 14:27
 */
@Service
public class CommentLikesRecordServiceImpl implements CommentLikesRecordService {

    @Autowired
    private CommentLikesRecordMapper commentLikesRecordMapper;

    @Autowired
    private CommentMapper commentMapper;


    /**
     *
     * @param commentLikesRecord
     * @return
     * 返回当前flag状态，点击后
     */
    @Override
    public int commentLikeAction(CommentLikesRecord commentLikesRecord) {
        int flag = commentLikesRecordMapper.isCommentRecordExist(commentLikesRecord);
        if(flag==1){
            if(commentLikesRecordMapper.getFlagByOthers(commentLikesRecord)==1){
                commentLikesRecordMapper.cancelLikeComment(commentLikesRecord);
                commentMapper.decreaseCommentLike(commentLikesRecord.getCommentId());
                return 0;
            }

            else {
                commentLikesRecordMapper.likeComment(commentLikesRecord);
                commentMapper.increaseCommentLike(commentLikesRecord.getCommentId());
                return 1;
            }
        }

        commentLikesRecordMapper.addLikeComment(commentLikesRecord);
        commentMapper.increaseCommentLike(commentLikesRecord.getCommentId());
        return 1;
    }


    @Override
    public List<String> getOwnCommentLikeId(long articleId, long userId) {
        List<String> commentIds = commentLikesRecordMapper.getOwnCommentLikeId(articleId,userId);
        return commentIds;
    }
}
