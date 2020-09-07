package com.peace.myblog.dto;

import lombok.Data;

/**
 * @author YR#
 * @create 2020-08-16 23:40
 */
@Data
public class BlogSeach {

    private String title;
    private Long categoryId;
    private Boolean recommend;
}
