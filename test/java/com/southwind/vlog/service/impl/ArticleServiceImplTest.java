package com.southwind.vlog.service.impl;

import com.github.pagehelper.PageInfo;
import com.southwind.vlog.VlogApplication;
import com.southwind.vlog.model.enity.Article;
import com.southwind.vlog.model.vo.ArticleVo;
import com.southwind.vlog.service.ArticleService;
import com.southwind.vlog.task.ArticleTask;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

import javax.annotation.Resource;

import java.util.List;
import java.util.concurrent.*;

import static org.junit.Assert.*;
@SpringBootTest(classes = VlogApplication.class)
@Slf4j
public class ArticleServiceImplTest {
@Resource
private ArticleService articleService;
@Resource
private ArticleTask articleTask;
    @Test
    public void insertArticles() {
        TimeUnit unit;
        BlockingQueue workQueue;
        ThreadPoolExecutor executor=new ThreadPoolExecutor(4, 8, 5, TimeUnit.SECONDS, new LinkedBlockingQueue<>(),new ThreadPoolExecutor.AbortPolicy());
        Future<List<Article>> future=executor.submit(articleTask);
        List<Article> articles=null;
        try{
            articles=future.get();
        }catch (InterruptedException|ExecutionException e){
            e.printStackTrace();
        }
        articleService.insertArticles(articles);
    }

    @Test
    void selectByPage(){
        PageInfo<ArticleVo> articlePageInfo=articleService.selectByPage(1,9);
        System.out.println(articlePageInfo.getList().size());
    }

    @Test
    void getRecommendArticles() {
        List<ArticleVo> recommendArticles=articleService.getRecommendArticles(1);
        recommendArticles.forEach(System.out::println);
    }

    @Test
    void getDetail() {
        Article detail=articleService.getDetail("004cd3da-ded4-4c79-a825-0621c3b6749e");
        System.out.println(detail);
    }
}