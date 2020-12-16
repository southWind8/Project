package com.southwind.vlog.service;

import com.southwind.vlog.model.enity.Article;

import java.util.List;

/**
 * @ClassName ArticleService
 * @Description TODO
 * @Author 86139
 * @Date 2020/12/15
 **/

public interface ArticleService {
    /**
     * 批量新文章
     * @param  articles 文章集合
     */
    void insertArticles(List<Article> articles);

    /**
     * 查询所有的文章
     * @return List<Article>
     */
    List<Article> selectAll();
}
