package com.southwind.vlog.service.impl;

import com.southwind.vlog.mapper.CommentMapper;
import com.southwind.vlog.model.enity.Comment;
import com.southwind.vlog.model.vo.CommentVo;
import com.southwind.vlog.service.CommentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @ClassName CommentServiceImpll
 * @Description TODO
 * @Author 南风
 * @Date 2020/12/22
 **/
@Service
public class CommentServiceImpl implements CommentService {
    @Resource
    private CommentMapper commentMapper;
    @Override
    public List<CommentVo> selectByArticleId(String articleId) {
        return commentMapper.selectByArticleId(articleId);
    }

    @Override
    public List<CommentVo> addComment(Comment comment) {
        //补充评论时间
        comment.setCreateTime(LocalDateTime.now());
        //插入
        commentMapper.addComment(comment);
        //查出这篇文章最新的评论列表
        return commentMapper.selectByArticleId(comment.getArticleId());
    }
}
