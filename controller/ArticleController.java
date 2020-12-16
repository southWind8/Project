package com.southwind.vlog.controller;

import com.southwind.vlog.common.ResponseResult;
import com.southwind.vlog.model.enity.Article;
import com.southwind.vlog.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @ClassName ArticleController
 * @Description TODO
 * @Author 南风
 * @Date 2020/12/15
 **/
@RestController
@RequestMapping(value = "/api/article")
@Slf4j
public class ArticleController {
    @Resource
    private ArticleService articleService;

    @GetMapping("all")
    public ResponseResult getArticles(){
        return ResponseResult.success(articleService.selectAll());
    }
}
