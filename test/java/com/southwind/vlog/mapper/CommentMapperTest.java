package com.southwind.vlog.mapper;

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
class CommentMapperTest {
    @Resource
    private CommentMapper commentMapper;
    @Test
    void selectByArticleId() {
        List<CommentVo> commentVos=commentMapper.selectByArticleId("1edfa7262f924501973ef3060915dc23");
        commentVos.forEach(commentVo -> System.out.println(commentVos));
    }

    @Test
    void addComment() {
        Comment comment= Comment.builder()
                .articleId("1edfa7262f924501973ef3060915dc23")
                .userId(1)
                .content("测试评论")
                .createTime(LocalDateTime.now())
                .build();
        commentMapper.addComment(comment);
    }
}