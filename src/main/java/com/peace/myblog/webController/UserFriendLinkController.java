package com.peace.myblog.webController;

import com.peace.myblog.daoObject.FriendLink;
import com.peace.myblog.response.CommonReturnType;
import com.peace.myblog.service.FriendLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author YR#
 * @create 2020-09-18 20:09
 */
@RestController
@RequestMapping("/user")
public class UserFriendLinkController {

    @Autowired
    private FriendLinkService friendLinkService;

    @GetMapping("/listFriendLink")
    public CommonReturnType listFriendLink() {
        List<FriendLink> friendLinks = friendLinkService.getAllFriendLink();



        if (friendLinks == null) {
            return CommonReturnType.create(null);

        } else {
            return CommonReturnType.create(friendLinks);
        }



    }
}
