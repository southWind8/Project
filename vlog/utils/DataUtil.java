package com.southwind.vlog.utils;

import com.southwind.vlog.model.Card;
import com.sun.org.apache.regexp.internal.RE;

import java.util.Arrays;
import java.util.List;

/**
 * @ClassName DataUtil
 * @Description TODO
 * @Author 86139
 * @Date 2020/12/3
 **/

public class DataUtil {
    public static List<Card> initCards() {
        Card[] cards = new Card[]{
                Card.builder()
                        .id(1)
                        .title("Java学习")
                        .bgImg("https://pic-go-hinoki.oss-cn-beijing.aliyuncs.com/share-app/java.png")
                        .content("Java学习")
                        .build(),
                Card.builder()
                        .id(1)
                        .title("Java Script学习")
                        .bgImg("https://pic-go-hinoki.oss-cn-beijing.aliyuncs.com/share-app/javascript.png")
                        .content("Java Script学习")
                        .build(),
                Card.builder()
                        .id(1)
                        .title("Linux学习")
                        .bgImg("https://pic-go-hinoki.oss-cn-beijing.aliyuncs.com/share-app/linux.png")
                        .content("Linux学习")
                        .build(),
                Card.builder()
                        .id(1)
                        .title("MySQL学习")
                        .bgImg("https://pic-go-hinoki.oss-cn-beijing.aliyuncs.com/share-app/mysql.png")
                        .content("SpringCloud学习")
                        .build(),
                Card.builder()
                        .id(1)
                        .title("Python学习")
                        .bgImg("https://pic-go-hinoki.oss-cn-beijing.aliyuncs.com/share-app/python.png")
                        .content("SpringCloud学习")
                        .build()
        };
        return Arrays.asList(cards);

    }
}
