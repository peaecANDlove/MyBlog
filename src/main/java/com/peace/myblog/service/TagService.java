package com.peace.myblog.service;

import com.peace.myblog.daoObject.Tag;
import com.peace.myblog.dto.TagCloud;

import java.util.List;

/**
 * @author YR#
 * @create 2020-08-16 13:25
 */
public interface TagService {


    /**
     * 保存一个标签
     * @param tag
     * @return
     */
    Tag saveTag(Tag tag);


    /**
     * 根据 id 获取一个标签
     * @param tagId
     * @return
     */
    Tag getTag(Long tagId);

    /**
     * 根据 id 更新一个标签
     * @param tag
     * @return
     */
    Tag updateTag(Tag tag);

    /**
     * 根据 id 删除一个标签
     * @param tagId
     */
    void deleteTag(Long tagId);

    /**
     * 获取所有标签，用于分页展示
     * @return
     */
    List<Tag> getAllTag();

    /**
     * 根据名称获取标签
     * @param name
     * @return
     */
    Tag getTagByName(String name);


    /**
     * 根据id获取一个博客的所有标签
     * @param ids
     * @return
     */
    List<Tag> getTagByBlog(List<Long> ids);

    /**
     * 根据Name获取一个博客的所有标签
     * @param name
     * @return
     */
    List<Tag> getTagByBlogTagName(List<String> name);


    List<TagCloud> getTagCloud();

}
