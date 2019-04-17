package com.paopao.chatservice.Util;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author ：paopao
 * @date ：Created in 2019/4/6 21:04
 * @description：放回结果工具类
 */

public class ResultUtil {

    private static final String SUCCESS="S";
    private static final String FAil="F";

    public static Map<String,Object> success(String success_msg){
        Map<String,Object> map=new LinkedHashMap<>();
        map.put("RESULT",SUCCESS);
        map.put("MESSAGE",success_msg);
        return map;
    }

    public static Map<String,Object> fail(String fail_msg){
        Map<String,Object> map=new LinkedHashMap<>();
        map.put("RESULT",FAil);
        map.put("MESSAGE",fail_msg);
        return map;
    }

}
