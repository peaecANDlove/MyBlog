package com.peace.myblog.dto;

import lombok.Data;

import java.util.Date;

/**
 * @author YR#
 * @create 2020-08-25 16:54
 */
@Data
public class WebsiteInfo {

    private Long totalBlog;
    private Long totalTag;
    private Long totalCategory;
    private Long totalComment;
    private Date websiteUpdateTime;
}
