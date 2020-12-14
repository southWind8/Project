package com.southwind.vlog.model.enity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName 实体标签
 * @Description TODO
 * @Author 南风
 * @Date 2020/12/13
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Tag {
    private Integer id;
    private String tagName;
    private String tagColor;
}
