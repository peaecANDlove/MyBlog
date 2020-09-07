package com.peace.myblog.mapper;

import com.peace.myblog.daoObject.FriendLink;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author YR#
 * @create 2020-08-20 17:06
 */
@Repository
public interface FriendLinkMapper {


    /**
     * 保存一个友链
     * @param friendLink
     * @return
     */
    @Insert("insert into t_friend_link(blog_name, blog_address, blog_info, avatar_address, blog_type) values(#{blogName}, #{blogAddress}, #{blogInfo}, #{avatarAddress}, #{blogType})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    Long saveFriendLink(FriendLink friendLink);


    /**
     * 根据 id 获取一个友链
     * @param friendLinkId
     * @return
     */
    @Select("select blog_name, blog_address, blog_info, avatar_address, blog_type, id from t_friend_link where id = #{friendLinkId}")
    FriendLink getFriendLink(Long friendLinkId);

    /**
     * 根据 id 更新一个友链
     * @param friendLink
     * @return
     */
    @Update("update t_friend_link set blog_name = #{blogName}, blog_address = #{blogAddress}, blog_info = #{blogInfo}, avatar_address = #{avatarAddress}, blog_type = #{blogType}  where id = #{id}")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void updateFriendLink(FriendLink friendLink);

    /**
     * 根据 id 删除一个友链
     * @param friendLinkId
     */
    @Delete("delete from t_friend_link where id = #{friendLinkId}")
    void deleteFriendLink(Long friendLinkId);

    /**
     * 获取所有友链，用于分页展示
     * @return
     */
    @Select("select blog_name, blog_address, blog_info, avatar_address, blog_type, id from t_friend_link")
    List<FriendLink> getAllFriendLink();



    @Select("select emotion_word, id, create_time from t_emotion_me where emotion_word = #{blogName}")
    FriendLink getBlogFriendLinkByName(String blogName);

}
