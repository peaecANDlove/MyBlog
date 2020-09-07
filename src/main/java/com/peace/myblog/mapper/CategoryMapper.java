package com.peace.myblog.mapper;

import com.peace.myblog.daoObject.Category;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author YR#
 * @create 2020-08-14 13:27
 */
@Repository
public interface CategoryMapper {

    /**
     * 保存一个分类
     * @param category
     * @return
     */
    @Insert("insert into t_category(name, create_time) values(#{name}, #{createTime})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    Long saveCategory(Category category);


    /**
     * 根据 id 获取一个分类
     * @param categoryId
     * @return
     */
    @Select("select name, id, create_time from t_category where id = #{categoryId}")
    Category getCategory(Long categoryId);

    /**
     * 根据 id 更新一个分类
     * @param category
     * @return
     */
    @Update("update t_category set name = #{name} where id = #{id}")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void updateCategory(Category category);

    /**
     * 根据 id 删除一个分类
     * @param categoryId
     */
    @Delete("delete from t_category where id = #{categoryId}")
    void deleteCategory(Long categoryId);

    /**
     * 获取所有分类，用于分页展示
     * @return
     */
    @Select("select name, id, create_time from t_category")
    List<Category> getAllCategory();


    @Select("select name, id, create_time from t_category where name = #{name}")
    Category getCategoryByName(String name);

    @Select("select count(1) from t_category")
    Long countAllCategory();

}
