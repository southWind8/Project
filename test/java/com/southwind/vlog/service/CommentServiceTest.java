package com.southwind.vlog.service;

import com.southwind.vlog.VlogApplication;
import com.southwind.vlog.model.enity.Comment;
import com.southwind.vlog.model.vo.CommentVo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


import javax.annotation.Resource;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(classes = VlogApplication.class)
@Slf4j
class CommentServiceTest {
@Resource
private CommentService commentService;
    @Test
    void selectByArticleId() {
        List<CommentVo> commentVos=commentService.selectByArticleId("1edfa7262f924501973ef3060915dc23");
        commentVos.forEach(System.out::println);
    }

    @Test
    void addComment() {
        Comment comment= Comment.builder()
                .articleId("1edfa7262f924501973ef3060915dc23")
                .userId(1)
                .content("陶然然的评论")
                .createTime(LocalDateTime.now())
                .build();
        List<CommentVo> commentVos=commentService.addComment(comment);
        commentVos.forEach(System.out::println);
    }
}