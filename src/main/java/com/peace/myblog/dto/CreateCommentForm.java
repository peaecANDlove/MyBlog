package com.peace.myblog.dto;

/**
 * @author YR#
 * @create 2021-03-01 20:00
 */
public class CreateCommentForm {

    private String nickName;

    private String content;

    private Long blogId;
    //被回复人
    private Long userReplayId;
    //父级评论id
    private Long parentCommentId;
}
