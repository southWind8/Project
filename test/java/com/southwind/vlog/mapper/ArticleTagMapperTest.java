package com.southwind.vlog.mapper;

import com.southwind.vlog.VlogApplication;
import com.southwind.vlog.model.enity.ArticleTag;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.internal.Classes;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
@SpringBootTest(classes = VlogApplication.class)
@Slf4j
public class ArticleTagMapperTest {
@Resource
private ArticleTagMapper articleTagMapper;
    @Test
    public void insetArticleTags() {
        List<ArticleTag>articleTagsList=new ArrayList<>();
        articleTagsList.add(ArticleTag.builder().articleId("1").tagName("leecode").build());
        articleTagsList.add(ArticleTag.builder().articleId("2").tagName("NLP").build());
        articleTagMapper.insertArticleTags(articleTagsList);
    }

    @Test
    public void selectByArticledId() {
    }
}