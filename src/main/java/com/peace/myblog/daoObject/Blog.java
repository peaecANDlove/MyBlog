package com.peace.myblog.daoObject;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author YR#
 * @create 2020-08-11 19:43
 * 博客单独类
 */

@Data
public class Blog {

    private Long id;
    private String title;
    private String content;
    private String firstPicture;
    private Long authorId;
    private String description;

//  分类id
    private Long categoryId;

//  标签 id 字符串
    private String tagNames;

//    原创=1，转载=2，翻译标签=3
    private String markFlag;

//    浏览次数
    private Integer views;

//    赞赏
    private Boolean appreciation;

//  版权
    private Boolean shareStatement;

//    评论
    private Boolean commentAble;

//    发布
    private Boolean published;

//    推荐
    private Boolean recommend;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;







}
