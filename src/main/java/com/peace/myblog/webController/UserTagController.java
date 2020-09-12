package com.peace.myblog.webController;

import com.peace.myblog.daoObject.Tag;
import com.peace.myblog.dto.TagCloud;
import com.peace.myblog.response.CommonReturnType;
import com.peace.myblog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author YR#
 * @create 2020-09-09 20:02
 */
@RequestMapping("/user")
@RestController
public class UserTagController {


    @Autowired
    private TagService tagService;

    @PostMapping("/fillAllTagInBox")
    public CommonReturnType fillAllTagInBox() {

        List<Tag> tags = tagService.getAllTag();
        return CommonReturnType.create(tags);
    }

    @GetMapping("/tagCloud")
    public CommonReturnType getTagCloudData() {

        List<TagCloud> tagClouds = tagService.getTagCloud();
        if (tagClouds.isEmpty()) {
            return CommonReturnType.create(null, "error");
        }

        return CommonReturnType.create(tagClouds);


    }
}
