package com.peace.myblog.service.Impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.peace.myblog.daoObject.Comment;
import com.peace.myblog.daoObject.User;
import com.peace.myblog.mapper.CommentLikesRecordMapper;
import com.peace.myblog.mapper.CommentMapper;
import com.peace.myblog.mapper.UserMapper;
import com.peace.myblog.service.CommentService;
import com.peace.myblog.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author YR#
 * @create 2020-08-25 16:51
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private CommentLikesRecordMapper commentLikesRecordMapper;

    @Override
    public Long countAllComment() {
        return commentMapper.countComment();
    }

    @Override
    public JSONObject addComment(Comment comment) {
        JSONObject jsonObject = new JSONObject();
        int flag = commentMapper.addComment(comment);

        if(flag==1){
            long responsorId = comment.getResponsorId();

            //不包括endindex,即要加一
            jsonObject.put("date", DateUtil.getStringTime().substring(0,16));
            if(responsorId!=0){
                jsonObject.put("responsorName",userMapper.getUserById(responsorId).getNickName());
            }

            jsonObject.put("status",200);
            jsonObject.put("remarkerName",userMapper.getUserById(comment.getRemarkerId()).getNickName());
            jsonObject.put("pid",comment.getCommentId());
            return jsonObject;
        }

        jsonObject.put("status",500);
        return jsonObject;
    }


    @Override
    public JSONObject getCommentsOfArticle(long articleId,long userId) {
        int commentNum = 0;
        List<String> ownCommentLikeId = new ArrayList<>();
        JSONArray parentJsonArray = new JSONArray();
        JSONObject returnJson = new JSONObject();

        if(userId!=-1){
            ownCommentLikeId = commentLikesRecordMapper.getOwnCommentLikeId(articleId, userId);
        }

        List<Comment> parents = commentMapper.getParentComments(articleId);
        for(Comment comment:parents){
            commentNum++;
            JSONArray childJsonArray = new JSONArray();
            User user = userMapper.getUserById(comment.getRemarkerId());
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("commentId",comment.getCommentId());
            jsonObject.put("content",comment.getCommentContent());
            jsonObject.put("date",comment.getCommentDate().substring(0,16));
            jsonObject.put("likes",comment.getLikes());
            jsonObject.put("avatarImgUrl",user.getAvatar());
            jsonObject.put("userName",user.getNickName());
            jsonObject.put("isLike",0);
            if(ownCommentLikeId.size()!=0){
                if(ownCommentLikeId.contains(Long.toString(comment.getCommentId()))){
                    jsonObject.put("isLike",1);
                }
            }

            List<Comment> children = commentMapper.getChildrenComments(articleId,comment.getCommentId());
            for(Comment child:children){
                commentNum++;
                JSONObject childJson = new JSONObject();
                User childUser = userMapper.getUserById(child.getRemarkerId());
                if(child.getResponsorId()!=0){
                    childJson.put("responsorName",userMapper.getUserById(child.getResponsorId()).getNickName());
                }

                childJson.put("responsorId",child.getResponsorId());
                childJson.put("commentId",child.getCommentId());
                childJson.put("remarkerId",child.getRemarkerId());
                childJson.put("content",child.getCommentContent());
                childJson.put("date",child.getCommentDate().substring(0,16));
                childJson.put("avatarImgUrl",childUser.getAvatar());
                childJson.put("userName",childUser.getNickName());
                childJsonArray.add(childJson);
            }

            jsonObject.put("replies",childJsonArray);
            parentJsonArray.add(jsonObject);
            returnJson.put("result",parentJsonArray);
            returnJson.put("commentNum",commentNum);
        }
        return returnJson;
    }


}
