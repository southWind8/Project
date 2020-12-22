package com.southwind.vlog.utils;

import java.util.Random;

/**
 * @ClassName StringUtil
 * @Description TODO
 * @Author 86139
 * @Date 2020/12/6
 **/

public class StringUtil {
    public static String   getVerifyCode(){
        String code="";
        Random random=new Random();
        for(int a=0;a<6;a++){
            code+=random.nextInt(10);
        }
        return code;
    }
}
