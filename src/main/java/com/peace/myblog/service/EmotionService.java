package com.peace.myblog.service;

import com.peace.myblog.daoObject.EmotionMe;

import java.util.List;

/**
 * @author YR#
 * @create 2020-08-19 13:29
 */
public interface EmotionService {

    /**
     * 保存一个心情
     * @param emotion
     * @return
     */
    EmotionMe saveEmotion(EmotionMe emotion);


    /**
     * 根据 id 获取一个心情
     * @param emotionId
     * @return
     */
    EmotionMe getEmotion(Long emotionId);

    /**
     * 根据 id 更新一个心情
     * @param emotion
     * @return
     */
    EmotionMe updateEmotion(EmotionMe emotion);

    /**
     * 根据 id 删除一个心情
     * @param emotionId
     */
    void deleteEmotion(Long emotionId);

    /**
     * 获取所有心情，用于分页展示
     * @return
     */
    List<EmotionMe> getAllEmotion();

    /**
     * 根据名称获取心情
     * @param name
     * @return
     */
    EmotionMe getEmotionByName(String name);
}
