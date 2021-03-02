package com.peace.myblog.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author YR#
 * @create 2020-08-21 16:27
 */
@Data
public class ArticleInfo {

    private Long id;
    private String title;
    private String authorName;
    private String description;
    private String publishDate;
    //  分类id
    private String category;

    // 标签 字符串
    private String tagNames;

    //    原创，转载，翻译标签
    private String markFlag;

    //    浏览次数
    private Integer views;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

}
