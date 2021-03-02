package com.peace.myblog.utils;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.peace.myblog.constant.SMSConstants;

/**
 * @author YR#
 * @create 2021-03-01 15:14
 */
public class SmsUtils {

    public static SendSmsResponse sendSms(String telephone, String code) throws ClientException {

        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");

        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", SMSConstants.ACCESS_KEY_ID, SMSConstants.ACCESS_KEY_SECRET);
        DefaultProfile.addEndpoint("cn-hangzhou","cn-hangzhou",SMSConstants.PRODUCT,SMSConstants.DOMAIN);


        IAcsClient client = new DefaultAcsClient(profile);
        SendSmsRequest sendSmsRequest = new SendSmsRequest();
        sendSmsRequest.setPhoneNumbers(telephone);
        sendSmsRequest.setSignName("翼灵团队");
        sendSmsRequest.setTemplateCode("SMS_161592670");
        sendSmsRequest.setTemplateParam("{\"code\":\""+code+"\"}");

        SendSmsResponse sendSmsResponse = client.getAcsResponse(sendSmsRequest);
        if(sendSmsResponse.getCode()!= null && sendSmsResponse.getCode().equals("OK")){
            System.out.println("短信发送成功！");
        }else {
            System.out.println("短信发送失败！");
        }
        return sendSmsResponse;

    }
}
