package com.peace.myblog.webController;

import com.peace.myblog.daoObject.EmotionMe;
import com.peace.myblog.response.CommonReturnType;
import com.peace.myblog.service.EmotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author YR#
 * @create 2020-09-18 20:58
 */
@RestController
@RequestMapping("/user")
public class UserAboutMeController {

    @Autowired
    private EmotionService emotionService;

    @GetMapping("/listEmotion")
    public CommonReturnType listEmotion() {
        List<EmotionMe> emotionMes = emotionService.getAllEmotion();
        if (emotionMes == null) {
            return CommonReturnType.create(null);

        } else {
            return CommonReturnType.create(emotionMes);
        }

    }
}
