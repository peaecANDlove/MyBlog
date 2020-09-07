package com.peace.myblog.mapper;

import com.peace.myblog.daoObject.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author YR#
 * @create 2020-08-12 19:58
 */

@Repository
public interface UserMapper {

    /**
     * 根据 username 查找用户
     * @param accountNumber
     * @return
     */

    @Select("select id, account_number, password, role, nick_name, avatar from t_user where account_number = #{accountNumber}")
    User getUserByAccountNumber(String accountNumber);



    /**
     * 创建用户
     * @param user
     * @return
     */
    @Insert("insert into t_user(nick_name, account_number, password, avatar, create_time, update_time) values(#{nickName}, #{accountNumber}, #{password}, #{avatar}, #{createTime}, #{updateTime}) ")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    Long createUser(User user);


    /**
     * 根据 id 更新一个用户
     * @param user
     * @return
     */
    @Update("update t_user set nick_name = #{nickName}, account_number = #{accountNumber}, password = #{password}, avatar = #{avatar},  update_time = #{updateTime} where id = #{id}")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void updateUser(User user);

    /**
     * 根据 id 删除一个用户
     * @param userId
     */
    @Delete("delete from t_user where id = #{userId}")
    void deleteUser(Long userId);

    /**
     * 获取所有用户，用于分页展示
     * @return
     */
    @Select("select nick_name, account_number, password, avatar, create_time, update_time, id, update_time, role from t_user")
    List<User> getAllUser();


    @Select("select nick_name, account_number, password, avatar, create_time, update_time, id, update_time from t_user where account_number = #{accountNumber}")
    User getUserByAccount(String accountNumber);


    @Select("select nick_name, id from t_user where id = #{userId}")
    User getUsernameAndId(Long userId);



}
