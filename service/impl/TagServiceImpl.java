package com.southwind.vlog.service.impl;

import com.southwind.vlog.mapper.TagMapper;
import com.southwind.vlog.model.enity.Tag;
import com.southwind.vlog.service.TagService;
import io.opentracing.tag.Tags;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName TagServiceImpl
 * @Description TODO
 * @Author 南风
 * @Date 2020/12/14
 **/
@Service
public class TagServiceImpl implements TagService {
    @Resource
    private TagMapper tagMapper;
    @Override
    public List<Tag> selectAll() {
        return tagMapper.selectAll();
    }
}
