package com.southwind.vlog.mapper;

import com.southwind.vlog.model.enity.Article;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @ClassName ArticleMapper
 * @Description TODO
 * @Author 南风
 * @Date 2020/12/15
 **/

public interface ArticleMapper {
        /**
         * 批量插入文章
         * @param  articleList 文章集合
         * @return int
         */
        @Insert({
                "<script>",
                "INSERT INTO t_article (id,category,user_id,title,cover,summary,content,url,publish_date,create_time) VALUES ",
                "<foreach collection='articleList' item='item' index='index' separator=','>",
                "(#{item.id}, #{item.category}, #{item.userId}, #{item.title}, #{item.cover}, #{item.summary}, #{item.content}," +
                        "#{item.url}, #{item.publishDate}, #{item.createTime})",
                "</foreach>",
                "</script>"
        })
        int insertArticles(@Param(value = "articleList") List<Article> articleList);
    /**
     * 查询所有文章
     * @return List<Article>
     */
    @Select("SELECT * FROM t_article ORDER BY create_time ")
    List<Article> selectAll();
}

