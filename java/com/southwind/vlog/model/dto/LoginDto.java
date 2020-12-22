package com.southwind.vlog.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName LoginDto
 * @Description TODO
 * @Author 86139
 * @Date 2020/12/5
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginDto {
    private String phone;
    private String password;

}
