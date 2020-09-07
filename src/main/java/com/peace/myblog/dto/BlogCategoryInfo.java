package com.peace.myblog.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author YR#
 * @create 2020-09-07 19:22
 */
@Data
public class BlogCategoryInfo {

    private Long id;
    private String title;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    private Integer views;

    private String categoryName;
    private Long categoryId;

    private String tagNames;
}
