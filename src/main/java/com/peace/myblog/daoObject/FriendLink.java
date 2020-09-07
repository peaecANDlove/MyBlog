package com.peace.myblog.daoObject;

import lombok.Data;

/**
 * @author YR#
 * @create 2020-08-12 12:41
 */

@Data
public class FriendLink {

    private Long id;
    private String blogName;
    private String blogAddress;
    private String blogInfo;
    private String avatarAddress;
    private String blogType;
}
