package com.peace.myblog.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.peace.myblog.daoObject.Category;
import com.peace.myblog.daoObject.Tag;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author YR#
 * @create 2020-08-16 22:14
 */
@Data
public class BlogModel {

    private Long id;
    private String title;
    private String content;
    private String firstPicture;
    private String authorName;
    private Long authorId;
    private String description;

    // 发布时间
    private String publishDate;

    //  分类id
    private Category category;

    //  标签
    private List<Tag> tags ;

    // 标签 字符串
    private String tagNames;

    //    原创，转载，翻译标签
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

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createTime;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date updateTime;
}
