package com.southwind.vlog.service;

import com.github.pagehelper.PageInfo;
import com.southwind.vlog.VlogApplication;
import com.southwind.vlog.model.vo.ArticleVo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import javax.swing.*;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(classes = VlogApplication.class)
@Slf4j
class ArticleServiceTest {
    @Resource
    private ArticleService articleService;

    @Test
    void selectByPage() {
        PageInfo<ArticleVo> articleVoPageInfo=articleService.selectByPage(1,1);
        System.out.println(articleVoPageInfo.getList().size());

    }
}