package com.southwind.vlog.controller;

import com.github.pagehelper.PageInfo;
import com.southwind.vlog.model.enity.Article;
import com.southwind.vlog.model.vo.ArticleVo;
import com.southwind.vlog.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

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

    @PostMapping("page")
    public PageInfo<ArticleVo> getArticlesByPage(@RequestParam(name = "pageNum",defaultValue = "1",required = false)int pageNum,
                                                 @RequestParam(name = "pageSize",defaultValue = "9",required = false)int pageSize){
        PageInfo<ArticleVo> articlePageInfo = articleService.selectByPage(pageNum,pageSize);
        if (articlePageInfo == null){
            throw  new NullPointerException();
        }
        return articlePageInfo;
    }


    @GetMapping("recommend")
    public List<ArticleVo> getRecommend(){
        List<ArticleVo> rocommengArticles = articleService.getRecommendArticles(getUserId());
        if (rocommengArticles==null){
            throw  new NullPointerException();
        }
        return rocommengArticles;
    }

    @GetMapping("{id}")
    public Article getArticleDatail(@PathVariable String id){
        Article detail = articleService.getDetail(id);
        if (detail == null){
            throw new NullPointerException();
        }
        return detail;

    }
    public int getUserId(){
        //通过RequestContextHolder来取得请求的request对象
        RequestAttributes at = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) at;
        assert sra !=null;
        HttpServletRequest request = sra.getRequest();
        //取消请求头
        return Integer.parseInt(request.getHeader("userId"));
    }

    @PostMapping("post")
    public Article postArticle(@RequestBody Article article){
        return articleService.postArticle(article);
    }
}
