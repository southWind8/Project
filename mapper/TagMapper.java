package com.southwind.vlog.mapper;

import com.southwind.vlog.model.enity.Tag;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @ClassName TagMapper
 * @Description TODO
 * @Author 86139
 * @Date 2020/12/14
 **/

public interface TagMapper {
    /**
     * 批量插入tag
     * @param  tagList 集合
     * @return int
     *
     */
    @Insert({"<script>",
            "INSERT INTO t_tag(tag_name,tag_color) VALUES",
            "<foreach collection='tagList' item='item' index='index' separator=','>",
            "(#{item.tagName},#{item.tagColor})",
            "</foreach>",
            "</script>"
    })
    @Options(useGeneratedKeys = true,keyProperty = "id")
    int insertTags(@Param(value = "tagList") List<Tag> tagList);
    /**
     * 查询所有标签
     * @return List<Tag>
     */
    @Select("SELECT *FROM t_tag")
    List<Tag> selectAll();
}
