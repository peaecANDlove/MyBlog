package com.peace.myblog.service.Impl;

import com.peace.myblog.daoObject.FriendLink;
import com.peace.myblog.error.MeNotFoundException;
import com.peace.myblog.mapper.FriendLinkMapper;
import com.peace.myblog.service.FriendLinkService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author YR#
 * @create 2020-08-20 17:22
 */
@Service
public class FriendLinkServiceImpl implements FriendLinkService {

    @Autowired
    private FriendLinkMapper friendLinkMapper;

    @Override
    @Transactional
    public FriendLink saveFriendLink(FriendLink friendLink) {
        friendLinkMapper.saveFriendLink(friendLink);
        return getFriendLink(friendLink.getId());
    }

    @Override
    @Transactional
    public FriendLink getFriendLink(Long friendLinkId) {
        return friendLinkMapper.getFriendLink(friendLinkId);
    }

    @Override
    @Transactional
    public FriendLink updateFriendLink(FriendLink friendLink) {
        FriendLink oldFriendLink = getFriendLink(friendLink.getId());
        if (oldFriendLink == null) {
            throw new MeNotFoundException("没有找到该友链");
        }

        BeanUtils.copyProperties(friendLink, oldFriendLink);
        friendLinkMapper.updateFriendLink(friendLink);
        return oldFriendLink;
    }

    @Override
    @Transactional
    public void deleteFriendLink(Long friendLinkId) {

        friendLinkMapper.deleteFriendLink(friendLinkId);
    }

    @Override
    @Transactional
    public List<FriendLink> getAllFriendLink() {
        return  friendLinkMapper.getAllFriendLink();
    }

    @Override
    @Transactional
    public FriendLink getFriendLinkByName(String name) {
        return friendLinkMapper.getBlogFriendLinkByName(name);
    }
}
