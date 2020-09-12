package com.peace.myblog.service.Impl;

import com.peace.myblog.daoObject.Tag;
import com.peace.myblog.dto.TagCloud;
import com.peace.myblog.error.MeNotFoundException;
import com.peace.myblog.mapper.TagsMapper;
import com.peace.myblog.service.TagService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author YR#
 * @create 2020-08-16 13:28
 */

@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagsMapper tagsMapper;

    @Override
    @Transactional
    public Tag saveTag(Tag tag) {
        tag.setCreateTime(new Date());
        tagsMapper.saveTag(tag);

        return getTag(tag.getId());
    }

    @Override
    @Transactional
    public Tag getTag(Long tagId) {
        return tagsMapper.getTag(tagId);
    }

    @Override
    @Transactional
    public Tag updateTag(Tag tag) {
        Tag updateTag = tagsMapper.getTag(tag.getId());
        if (updateTag == null) {
            throw new MeNotFoundException("标签不存在");
        }

        BeanUtils.copyProperties(tag, updateTag);
        tagsMapper.updateTag(updateTag);

        return getTag(updateTag.getId());
    }

    @Override
    @Transactional
    public void deleteTag(Long tagId) {
        tagsMapper.deleteTag(tagId);
    }

    @Override
    @Transactional
    public List<Tag> getAllTag() {
        return tagsMapper.getAllTag();
    }

    @Override
    @Transactional
    public Tag getTagByName(String name) {
        return tagsMapper.getTagByName(name);
    }

    @Override
    @Transactional
    public List<Tag> getTagByBlog(List<Long> ids) {
        return  tagsMapper.getTagsByBlog(ids);
    }

    @Override
    public List<Tag> getTagByBlogTagName(List<String> name) {
        return tagsMapper.getTagsByBlogTagName(name);
    }


    @Override
    public List<TagCloud> getTagCloud() {
        List<Tag> tags = this.getAllTag();

        if (!tags.isEmpty()) {
            List<TagCloud> tagClouds = new ArrayList<>();

            for (Tag tag: tags) {
                TagCloud tagCloud = new TagCloud();
                tagCloud.setTagId(tag.getId());
                tagCloud.setTagName(tag.getTagName());
                tagCloud.setTagSize((int)((Math.random()*7))+10);
                tagClouds.add(tagCloud);
            }

            return tagClouds;
        }

        return null;
    }
}
