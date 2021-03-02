package com.peace.myblog.webController;

import com.alibaba.fastjson.JSONObject;
import com.peace.myblog.daoObject.Comment;
import com.peace.myblog.daoObject.CommentLikesRecord;
import com.peace.myblog.daoObject.User;
import com.peace.myblog.mapper.UserMapper;
import com.peace.myblog.service.CommentLikesRecordService;
import com.peace.myblog.service.CommentService;
import com.peace.myblog.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * @author YR#
 * @create 2021-03-01 19:51
 */
@RestController
@RequestMapping("/user")
public class UserCommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private CommentLikesRecordService commentLikesRecordService;

    @Autowired
    private UserMapper userMapper;


    @RequestMapping("/addComment")
    public JSONObject addComment(Comment comment, @AuthenticationPrincipal Principal principal){

        User user = userMapper.getUserByAccountNumber(principal.getName());
        comment.setRemarkerId(user.getId());
        comment.setCommentDate(DateUtil.getStringTime());

        return commentService.addComment(comment);
    }


    @RequestMapping("/getCommentsOfArticle")
    public JSONObject getCommentsOfArticle(long articleId,@AuthenticationPrincipal Principal principal){
        long userId = -1;
        if(principal!=null){
            User user = userMapper.getUserByAccountNumber(principal.getName());
            userId = user.getId();
        }
        return commentService.getCommentsOfArticle(articleId,userId);
    }


    @RequestMapping("/commentLikeAction")
    public int commentLikeAction(CommentLikesRecord commentLikesRecord, @AuthenticationPrincipal Principal principal){
        User user = userMapper.getUserByAccountNumber(principal.getName());
        commentLikesRecord.setLikerId(user.getId());
        commentLikesRecord.setDate(DateUtil.getStringTime());
        return commentLikesRecordService.commentLikeAction(commentLikesRecord);
    }

}
