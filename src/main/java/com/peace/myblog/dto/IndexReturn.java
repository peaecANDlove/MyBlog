package com.peace.myblog.dto;

import lombok.Data;

import java.util.List;

/**
 * @author YR#
 * @create 2020-08-22 16:29
 */
@Data
public class IndexReturn {

    private List<ArticleInfo> articleInfos;
    private MyPageInfo myPageInfo;
}
