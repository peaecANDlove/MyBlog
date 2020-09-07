package com.peace.myblog.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author YR#
 * @create 2020-08-17 13:33
 * 将字符串转换成 Long 类型的 list
 */
public class StringBothConvertLongArray {

    public static List<Long> convertToLongArray(String s) {


        List<Long> ids = new ArrayList<>();
        if (!"".equals(s) && s !=null) {
            String[] strings = s.split(",");
            for (String s1 : strings) {
                ids.add(Long.valueOf(s1));
            }
        }

        return ids;
    }

    public static List<String> convertToStringList(String s) {

        List<String> tagNames = new ArrayList<>();
        if (!"".equals(s) && s !=null) {
            String[] strings = s.split(",");
            tagNames.addAll(Arrays.asList(strings));
        }

        return tagNames;

    }

    public static String convertToString(List<Long> ids) {

        StringBuffer stringBuffer = new StringBuffer();

        if (ids != null){
            for (int i=0; i<ids.size(); i++) {
                if (!ids.get(i).equals(ids.get(ids.size()-1))) {
                    stringBuffer.append(ids.get(i)).append(",");

                } else {
                    stringBuffer.append(ids.get(i));
                }
            }
        }


        return stringBuffer.toString();
    }







}
