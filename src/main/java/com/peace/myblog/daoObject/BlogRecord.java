package com.peace.myblog.daoObject;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author YR#
 * @create 2020-08-12 12:44
 * 博客记录类
 */
@Data
public class BlogRecord {

    private Long id;
    private String recordWord;

    @JsonFormat(pattern = "yyyy年MM月dd日")
    private Date createTime;
}
