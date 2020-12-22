package com.southwind.vlog.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName WxLoginDto
 * @Description TODO
 * @Author 南风
 * @Date 2020/12/8
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class WxLoginDto {
    private String wxOpenId;
    private  String nickname;
    private  Integer gender;
    private String avatar;
}
