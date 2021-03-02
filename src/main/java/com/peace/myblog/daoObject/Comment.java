package com.peace.myblog.daoObject;

import lombok.Data;

/**
 * @author YR#
 * @create 2020-08-11 19:59
 */
@Data
public class Comment {
    /**
     * 评论的id编号
     */
    private long commentId;


    /**
     * 文章id
     */
    private long articleId;

    /**
     * 评论者id
     */
    private long remarkerId;

    /**
     * 回复者id
     */
    private long responsorId;

    /**
     * 父级id
     */
    private long pId;

    /**
     * 评论时间
     */
    private String commentDate;

    /**
     * 评论内容
     */
    private String commentContent;


    /**
     * 喜欢数目
     */
    private int likes;

    /**
     * 管理员是否阅读
     */
    private int isRead;
}
