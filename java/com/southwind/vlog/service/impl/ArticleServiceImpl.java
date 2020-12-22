package com.southwind.vlog.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.southwind.vlog.mapper.ArticleMapper;
import com.southwind.vlog.mapper.ArticleTagMapper;
import com.southwind.vlog.model.enity.Article;
import com.southwind.vlog.model.enity.ArticleTag;
import com.southwind.vlog.model.vo.ArticleVo;
import com.southwind.vlog.service.ArticleService;
import com.southwind.vlog.utils.DataUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
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
        assert articles != null;
        articles.forEach(article -> {
            if (article.getTagList() != null) {
                articleTagMapper.insertArticleTags(article.getTagList());
            }
        });
    }

    @Override
    public List<ArticleVo> getRecommendArticles(int userId) {
        return articleMapper.getRecommendArticles();
    }

    @Override
    public PageInfo<ArticleVo> selectByPage(int pageNum, int pageSize) {
        //将参数传给这个方法就可以实现物理分页了，非常简单
        PageHelper.startPage(pageNum, pageSize);
        //先根据用户id查到所有数据
        Page<ArticleVo> articlePage = articleMapper.selectAll();
        //将这些数据作为入参构建出PageInfo(包含了总页数，当前页码，每夜数量，当前页数据List等等一堆属性和方法)
        return new PageInfo<>(articlePage);
    }

    @Override
    public Article getDetail(String id) {
        return articleMapper.getDetail(id);
    }

    @Override
    public Article postArticle(Article article) {
        article.setCover("https://picsum.photos/1920/1080?random&and="+Math.random());
        article.setCreateTime(LocalDateTime.now());
        article.setTotalWords(DataUtil.getTotalWords());
        article.setDuration(DataUtil.getDuration());
        article.setPageView(DataUtil.getPageView());
        System.out.println(article);
        //新增文章
        articleMapper.add(article);
        //获得文章的标签列表
        List<ArticleTag> tagList = article.getTagList();
        //批量插入标签
        articleTagMapper.insertArticleTags(tagList);
        return article;
    }

}