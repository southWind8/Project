package com.southwind.vlog.service;

import com.southwind.vlog.model.enity.Tag;

import java.util.List;

/**
 * @ClassName TagService
 * @Description TODO
 * @Author 86139
 * @Date 2020/12/14
 **/

public interface TagService {
    /**
     * 查询所有标签
     * @return 所有标签
     */
    List<Tag> selectAll();
}
