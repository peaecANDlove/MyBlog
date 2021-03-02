package com.peace.myblog.daoObject;

import lombok.Data;

/**
 * @author YR#
 * @create 2021-03-02 14:23
 */
@Data
public class CommentLikesRecord {

    private long recordId;

    private long articleId;

    private long commentId;

    private long likerId;

    /**
     * 1:点赞
     * 0：取消点赞
     */
    private int flag;

    private String date;
}
