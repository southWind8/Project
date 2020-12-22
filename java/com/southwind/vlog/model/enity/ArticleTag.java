package com.southwind.vlog.model.enity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName ArticleTag
 * @Description TODO
 * @Author 南风
 * @Date 2020/12/15
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ArticleTag {
    private Integer id;
    private String articleId;
    private String tagName;
}
