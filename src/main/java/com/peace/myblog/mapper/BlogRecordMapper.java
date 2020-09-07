package com.peace.myblog.mapper;

import com.peace.myblog.daoObject.BlogRecord;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * @author YR#
 * @create 2020-08-19 11:44
 */
@Repository
public interface BlogRecordMapper {

    /**
     * 保存一个记录
     * @param record
     * @return
     */
    @Insert("insert into t_blog_record(record_word, create_time) values(#{recordWord}, #{createTime})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    Long saveRecord(BlogRecord record);


    /**
     * 根据 id 获取一个记录
     * @param recordId
     * @return
     */
    @Select("select record_word, id, create_time from t_blog_record where id = #{recordId}")
    BlogRecord getRecord(Long recordId);

    /**
     * 根据 id 更新一个记录
     * @param record
     * @return
     */
    @Update("update t_blog_record set record_word = #{recordWord} where id = #{id}")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void updateRecord(BlogRecord record);

    /**
     * 根据 id 删除一个记录
     * @param recordId
     */
    @Delete("delete from t_blog_record where id = #{recordId}")
    void deleteRecord(Long recordId);

    /**
     * 获取所有记录，用于分页展示
     * @return
     */
    @Select("select record_word, id, create_time from t_blog_record")
    List<BlogRecord> getAllBlogRecord();



    @Select("select record_word, id, create_time from t_blog_record where record_word = #{recordWord}")
    BlogRecord getBlogRecordByName(String recordWord);

    @Select(" select create_time from t_blog_record order by id DESC limit 1")
    Date getBlogRecordTime();
}
