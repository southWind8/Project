package com.southwind.vlog.service.impl;

import com.southwind.vlog.mapper.ArticleMapper;
import com.southwind.vlog.mapper.ArticleTagMapper;
import com.southwind.vlog.model.enity.Article;
import com.southwind.vlog.model.enity.ArticleTag;
import com.southwind.vlog.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName ArticleServiceImpl
 * @Description TODO
 * @Author 南风
 * @Date 2020/12/15
 **/
@Service
@Slf4j
public class ArticleServiceImpl implements ArticleService {
    @Resource
    private ArticleMapper articleMapper;
    @Resource
    private ArticleTagMapper articleTagMapper;
    @Override
    public void insertArticles(List<Article> articles) {
        articleMapper.insertArticles(articles);
        assert articles!=null;
        articles.forEach(article -> {
            if(article.getTagList()!=null){
                articleTagMapper.insetArticleTags(article.getTagList());
            }
        });
    }

    @Override
    public List<Article> selectAll() {
        List<Article> articles=articleMapper.selectAll();
        articles.forEach(article -> {
            List<ArticleTag> articleTags=articleTagMapper.selectByArticledId(article.getId());
            article.setTagList(articleTags);
        });
        return articles;
    }
}
