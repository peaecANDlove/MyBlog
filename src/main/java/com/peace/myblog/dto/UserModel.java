package com.peace.myblog.dto;

import lombok.Data;

/**
 * @author YR#
 * @create 2020-08-14 0:22
 */
@Data
public class UserModel {

    private Long id;
    private String accountNumber;
    private String nickName;
    private String avatar;
    private Integer role;
}
