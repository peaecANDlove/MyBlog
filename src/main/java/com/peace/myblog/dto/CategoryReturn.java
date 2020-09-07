package com.peace.myblog.dto;

import lombok.Data;

import java.util.List;

/**
 * @author YR#
 * @create 2020-09-07 20:56
 */
@Data
public class CategoryReturn {

    private List<BlogCategoryInfo> blogCategoryInfos;
    private MyPageInfo myPageInfo;
}
