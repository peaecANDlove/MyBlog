package com.peace.myblog.service.Impl;

import com.peace.myblog.daoObject.EmotionMe;
import com.peace.myblog.error.MeNotFoundException;
import com.peace.myblog.mapper.EmotionMapper;
import com.peace.myblog.service.EmotionService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author YR#
 * @create 2020-08-19 13:32
 */
@Service
public class EmotionServiceImpl implements EmotionService {

    @Autowired
    private EmotionMapper emotionMapper;

    @Override
    public EmotionMe saveEmotion(EmotionMe emotion) {
        emotion.setCreateTime(new Date());
        emotionMapper.saveEmotion(emotion);
        return getEmotion(emotion.getId());
    }

    @Override
    public EmotionMe getEmotion(Long emotionId) {
        return emotionMapper.getEmotion(emotionId);
    }

    @Override
    public EmotionMe updateEmotion(EmotionMe emotion) {
        EmotionMe emotionMe = emotionMapper.getEmotion(emotion.getId());
        if (emotionMe == null) {
            throw new MeNotFoundException("心情不存在");
        }

        BeanUtils.copyProperties(emotion, emotionMe);
        emotionMapper.updateEmotion(emotion);
        return emotionMe;
    }

    @Override
    public void deleteEmotion(Long emotionId) {
        emotionMapper.deleteEmotion(emotionId);
    }

    @Override
    public List<EmotionMe> getAllEmotion() {
        return emotionMapper.getAllBlogEmotion();
    }

    @Override
    public EmotionMe getEmotionByName(String name) {
        return emotionMapper.getBlogEmotionByName(name);
    }
}
