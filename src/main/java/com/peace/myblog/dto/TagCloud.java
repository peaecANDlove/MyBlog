package com.peace.myblog.dto;

import lombok.Data;

/**
 * @author YR#
 * @create 2020-09-12 20:01
 */
@Data
public class TagCloud {

    private Long tagId;
    private String tagName;
    private Integer tagSize;
}
