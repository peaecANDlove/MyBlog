package com.peace.myblog.daoObject;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author YR#
 * @create 2020-08-11 19:52
 */
@Data
public class Tag {

    private Long id;
    private String tagName;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createTime;
}
