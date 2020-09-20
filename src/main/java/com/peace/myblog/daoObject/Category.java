package com.peace.myblog.daoObject;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Date;

/**
 * @author YR#
 * @create 2020-08-11 19:51
 * 分类
 */
@Data
public class Category {

    private Long id;

    @NotBlank(message = "分类名不能为空")
    private String name;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createTime;


}
