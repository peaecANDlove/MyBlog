package com.peace.myblog.mapper;

import com.peace.myblog.daoObject.EmotionMe;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author YR#
 * @create 2020-08-19 13:21
 */
@Repository
public interface EmotionMapper {

    /**
     * 保存一个心情
     * @param emotion
     * @return
     */
    @Insert("insert into t_emotion_me(emotion_word, create_time) values(#{emotionWord}, #{createTime})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    Long saveEmotion(EmotionMe emotion);


    /**
     * 根据 id 获取一个心情
     * @param emotionId
     * @return
     */
    @Select("select emotion_word, id, create_time from t_emotion_me where id = #{emotionId}")
    EmotionMe getEmotion(Long emotionId);

    /**
     * 根据 id 更新一个心情
     * @param emotion
     * @return
     */
    @Update("update t_emotion_me set emotion_word = #{emotionWord} where id = #{id}")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void updateEmotion(EmotionMe emotion);

    /**
     * 根据 id 删除一个心情
     * @param emotionId
     */
    @Delete("delete from t_emotion_me where id = #{emotionId}")
    void deleteEmotion(Long emotionId);

    /**
     * 获取所有心情，用于分页展示
     * @return
     */
    @Select("select emotion_word, id, create_time from t_emotion_me")
    List<EmotionMe> getAllBlogEmotion();



    @Select("select emotion_word, id, create_time from t_emotion_me where emotion_word = #{emotionWord}")
    EmotionMe getBlogEmotionByName(String emotionWord);
}
