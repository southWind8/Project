package com.southwind.vlog.mapper;
import com.southwind.vlog.mapper.ArticleMapper;

import com.github.pagehelper.Page;
import com.southwind.vlog.VlogApplication;
import com.southwind.vlog.model.enity.Article;
import com.southwind.vlog.model.vo.ArticleVo;
import com.southwind.vlog.task.ArticleTask;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.southwind.vlog.VlogApplication;
import com.southwind.vlog.mapper.ArticleMapper;
import com.github.pagehelper.Page;
import com.mysql.cj.util.TimeUtil;
import com.southwind.vlog.VlogApplication;
import com.southwind.vlog.model.enity.Article;
import com.southwind.vlog.task.ArticleTask;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import java.util.List;
import java.util.concurrent.*;

import static org.junit.jupiter.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(classes = VlogApplication.class)
@Slf4j
class ArticleMapperTest {

    @Resource
    private ArticleMapper articleMapper;
    @Resource
    private ArticleTask articleTask;

    @Test
    void insertArticle() throws Exception {
        ThreadPoolExecutor executor=new ThreadPoolExecutor(4, 8, 5, TimeUnit.SECONDS ,new SynchronousQueue<>());
        Future<List<Article>> future = executor.submit(articleTask);
        List<Article> articles = future.get();
        int count = articleMapper.insertArticles(articles);
        System.out.println(count);
    }

    @Test
    void selectAll() {
        Page<ArticleVo> articlePage = articleMapper.selectAll();
        System.out.println(articlePage.toPageInfo().getList().size());

    }
}