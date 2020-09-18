package com.peace.myblog.daoObject;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author YR#
 * @create 2020-08-12 12:46
 */
@Data
public class EmotionMe {

    private Long id;
    private String emotionWord;
    private String category;

    @JsonFormat(pattern = "yyyy年MM月dd日")
    private Date createTime;
}
