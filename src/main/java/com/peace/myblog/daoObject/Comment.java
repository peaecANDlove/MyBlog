package com.peace.myblog.daoObject;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author YR#
 * @create 2020-08-11 19:59
 */
@Data
public class Comment {

//    评论Id
    private Long id;

//    评论用户Id
    private Long userCommentId;

//    回复评论用户Id
    private Long userReplayId;

//    父级评论Id
    private Long parentCommentId;

//    评论内容
    private String content;

//    评论用户头像
    private String avatar;

//    创建时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

//    博客id
    private Long blogId;
}
