package com.peace.myblog.mapper;

import com.peace.myblog.daoObject.Blog;
import com.peace.myblog.dto.BlogCategoryInfo;
import com.peace.myblog.dto.BlogSeach;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author YR#
 * @create 2020-08-16 16:30
 */
@Repository
public interface BlogMapper {


    @Select("select id, title, description, content, first_picture, publish_date,  author_id, category_id, tag_names, mark_flag, views, appreciation, share_statement, comment_able, published, recommend, create_time, update_time " +
            "from t_blog where id = #{id}")
    Blog getBlogById(Long id);

    @Insert("insert into t_blog(title, description, content, first_picture, publish_date, author_id, category_id, tag_names, mark_flag, views, appreciation, share_statement, comment_able, published, recommend, create_time, update_time) " +
            "values(#{title}, #{description}, #{content}, #{firstPicture}, #{publishDate},   #{authorId}, #{categoryId}, ifnull(#{tagNames}, tag_names), ifnull(#{markFlag}, mark_flag), ifnull(#{views}, views), ifnull(#{appreciation}, appreciation), ifnull(#{shareStatement}, share_statement), ifnull(#{commentAble}, comment_able), #{published}, ifnull(#{recommend}, recommend), #{createTime}, #{updateTime})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void saveBlog(Blog blog);

    @Update("update t_blog set title = #{title}, content = #{content}, first_picture = #{firstPicture},  author_id = #{authorId}, category_id = #{categoryId}, " +
            "tag_names = ifnull(#{tagNames}, tag_names), mark_flag = ifnull(#{markFlag}, mark_flag), " +
            "views = ifnull(#{views}, views), appreciation = ifnull(#{appreciation}, appreciation), " +
            "share_statement = ifnull(#{shareStatement}, share_statement), comment_able = ifnull(#{commentAble}, comment_able), " +
            "recommend = #{recommend}, description = #{description}, published = #{published}, create_time = ifnull(#{createTime}, create_time), update_time = #{updateTime} " +
            "where id = #{id}")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void updateBlog(Blog blog);

    @Delete("delete from t_blog where id = #{id}")
    void deleteBlogById(Long id);

    @Select("select id, title, content, first_picture,  author_id, category_id, tag_names, " +
            "mark_flag, views, appreciation, description, share_statement, comment_able, published, recommend, create_time, update_time " +
            "from t_blog where " +
            "title like ifnull(CONCAT('%',#{title},'%') , title) and " +
            "category_id = ifnull(#{categoryId}, category_id)  and " +
            "recommend = ifnull(#{recommend}, recommend)" )
    List<Blog> getAllBlog(BlogSeach blog);

    @Select("select id, title, author_id, description, publish_date, category_id, tag_names, mark_flag, views,  create_time, update_time " +
            "from t_blog ")
    List<Blog> getAllBlogSimple();


    /**
     * 推荐查询语句
     * @return
     */
    @Select("select id, title, views from t_blog order by views asc limit 0,5")
    List<Blog> recommendBlog();

    @Select("select count(1) from t_blog")
    Long countAllBlog();

    @Select("select count(*) from t_blog where category_id = #{categoryId}  ")
    Integer getNumberOfBlogByCategoeyId(Long categoryId);

    @Select("select title, create_time, views, category_id, id, tag_names from t_blog ")
    List<BlogCategoryInfo> getBlogCategoryMerge();

    @Select("select count(*) from t_blog where publish_date = #{archiveName} ")
    Integer getArchiveNumber(String archiveName);

    @Select("select title, create_time, views, category_id, id, tag_names from t_blog where publish_date = #{publishDate}")
    List<BlogCategoryInfo> getBlogByPublishDate(String publishDate);

    @Select("select title, create_time, views, category_id, id, tag_names from t_blog where category_id = #{categoryId}")
    List<BlogCategoryInfo> getBlogByCategoryId(Long categoryId);

    @Select("select title, create_time, views, category_id, id, tag_names from t_blog where tag_names like concat('%',#{tag},'%') order by create_time desc")
    List<BlogCategoryInfo> getBlogByTagName(String categoryName);


    @Select("select title, create_time, views, category_id, id, tag_names from t_blog where category_id = ifnull(#{categoryId}, category_id)")
    List<BlogCategoryInfo> getBlogCategory(Long categoryId);

    @Select("select title, create_time, views, category_id, id, tag_names from t_blog where publish_date = ifnull(#{publishDate}, publish_date)")
    List<BlogCategoryInfo> getBlogCategoryByTime(String publishDate);
}
