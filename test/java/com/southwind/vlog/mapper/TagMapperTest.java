package com.southwind.vlog.mapper;

import com.southwind.vlog.VlogApplication;
import com.southwind.vlog.model.enity.Tag;
import com.southwind.vlog.task.TagTask;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import java.util.List;
import java.util.concurrent.*;

import static org.junit.Assert.*;
@SpringBootTest(classes = VlogApplication.class)
public class TagMapperTest {
@Resource
private TagTask tagTask;
@Resource
private TagMapper tagMapper;
    @Test
    void batchInsert() throws Exception{
        //线程池核心线程数为6，最大线程数为10，超时间为5s
        ThreadPoolExecutor executor=new ThreadPoolExecutor(4, 8,5 ,
                TimeUnit.SECONDS, new SynchronousQueue<>());
        Future<List<Tag>> future=executor.submit(tagTask);
        List<Tag> tags=future.get();
        int count=tagMapper.insertTags(tags);
        System.out.println(count);
    }

    @Test
    public void selectAll() {
        List<Tag> tags=tagMapper.selectAll();
        tags.forEach(tag -> System.out.println(tag.getTagName()+","+tag.getTagColor()));
    }
}