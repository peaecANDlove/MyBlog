package com.peace.myblog.service;

import com.peace.myblog.daoObject.FriendLink;

import java.util.List;

/**
 * @author YR#
 * @create 2020-08-20 17:22
 */
public interface FriendLinkService {

    /**
     * 保存一个友链
     * @param friendLink
     * @return
     */
    FriendLink saveFriendLink(FriendLink friendLink);


    /**
     * 根据 id 获取一个友链
     * @param friendLinkId
     * @return
     */
    FriendLink getFriendLink(Long friendLinkId);

    /**
     * 根据 id 更新一个友链
     * @param friendLink
     * @return
     */
    FriendLink updateFriendLink(FriendLink friendLink);

    /**
     * 根据 id 删除一个友链
     * @param friendLinkId
     */
    void deleteFriendLink(Long friendLinkId);

    /**
     * 获取所有友链，用于分页展示
     * @return
     */
    List<FriendLink> getAllFriendLink();

    /**
     * 根据名称获取友链
     * @param name
     * @return
     */
    FriendLink getFriendLinkByName(String name);
}
