package com.peace.myblog.mapper;

import com.peace.myblog.daoObject.Tag;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author YR#
 * @create 2020-08-16 13:16
 */
@Repository
public interface TagsMapper {

    /**
     * 保存一个标签
     * @param tag
     * @return
     */
    @Insert("insert into t_tag(tag_name, create_time) values(#{tagName}, #{createTime})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    Long saveTag(Tag tag);


    /**
     * 根据 id 获取一个标签
     * @param tagId
     * @return
     */
    @Select("select tag_name, id, create_time from t_tag where id = #{tagId}")
    Tag getTag(Long tagId);

    /**
     * 根据 id 更新一个标签
     * @param tag
     * @return
     */
    @Update("update t_tag set tag_name = #{tagName} where id = #{id}")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void updateTag(Tag tag);

    /**
     * 根据 id 删除一个标签
     * @param tagId
     */
    @Delete("delete from t_tag where id = #{tagId}")
    void deleteTag(Long tagId);

    /**
     * 获取所有标签，用于分页展示
     * @return
     */
    @Select("select tag_name, id, create_time from t_tag")
    List<Tag> getAllTag();

    /**
     * 查询标签总数
     * @return
     */
    @Select("select count(1) from t_tag")
    Long countAllTag();

    @Select("select tag_name, id, create_time from t_tag where tag_name = #{tagName}")
    Tag getTagByName(String name);

    @Select({
        "<script>" +
            "select * from t_tag where id in " +
            "<foreach item = 'item' index = 'index' collection = 'ids' open='(' separator=',' close=')'>" +
                "#{item}" +
            "</foreach>"+
        "</script>"
    })
    List<Tag> getTagsByBlog(List<Long> ids);


    @Select({
            "<script>" +
                    "select * from t_tag where tag_name in " +
                    "<foreach item = 'item' index = 'index' collection = 'tagNames' open='(' separator=',' close=')'>" +
                    "#{item}" +
                    "</foreach>"+
            "</script>"
    })
    List<Tag> getTagsByBlogTagName(List<String> tagNames);


}
