package com.peace.myblog.mapper;

import com.peace.myblog.daoObject.Comment;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author YR#
 * @create 2020-08-25 16:50
 */
@Repository
public interface CommentMapper {

    @Select("select count(1) from t_comment")
    Long countComment();

    /**
     * 添加评论
     * @param comment
     * @return
     */
    @Insert("insert into t_comment(blog_id,remarker_id,responsor_id,p_id,comment_content,likes,comment_date,is_read) values(#{articleId},#{remarkerId},#{responsorId},#{pId},#{commentContent},#{likes},#{commentDate},#{isRead})")
    @Options(useGeneratedKeys = true,keyProperty = "commentId")
    int addComment(Comment comment);

    /**
     * 获得一级评论
     * @param articleId
     * @return
     */
    @Select("select *from t_comment where blog_id=#{articleId} and p_id=0 order by comment_date desc")
    List<Comment> getParentComments(long articleId);

    /**
     * 获得子评论
     * @param articleId
     * @param commentId
     * @return
     */
    @Select("select *from t_comment where blog_id=#{articleId} and p_id=#{commentId}")
    List<Comment> getChildrenComments(@Param("articleId") long articleId, @Param("commentId") long commentId);

    /**
     * 点赞
     * @param commentId
     * @return
     */
    @Update("update t_comment set likes=likes+1 where comment_id=#{commentId}")
    int increaseCommentLike(long commentId);

    /**
     * 取消点赞
     * @param commentId
     * @return
     */
    @Update("update t_comment set likes=likes-1 where comment_id=#{commentId}")
    int decreaseCommentLike(long commentId);

    /**
     * 获得用户的评论
     * @param id
     * @return
     */
    @Select("select *from t_comment where remarker_id=#{id}")
    List<Comment> getCommentsOfUser(long id);


    /**
     * 得到@我的
     * @param id
     * @return
     */
    @Select("select comment_id,remarker_id,blog_id,comment_content,left(comment_date,10) comment_date from t_comment where responsor_id=#{id} order by comment_date desc")
    List<Comment> getReferMineComments(long id);


    /**
     * 得到用户发表的一级评论
     * @param id
     * @return
     */
    @Select("select comment_id from t_comment where remarker_id=#{id} and p_id=0")
    List<String> getParentReplyCommentsId(long id);

    /**
     * 得到用户在一级评论下除开@别人的回复
     * @param parentIds
     * @return
     */
    @Select("<script>select comment_id,remarker_id,blog_id,comment_content,left(comment_date,10) comment_date from t_comment where responsor_id=0 and p_id in " +
            "<foreach collection='list' item='item' open='(' separator=',' close=')'>#{item}</foreach> order by comment_date desc" +
            "</script>")
    List<Comment> getChildrenReplyComments(List<String> parentIds);


}
