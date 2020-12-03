package com.southwind.vlog.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName ResponseResult
 * @Description TODO
 * @Author 86139
 * @Date 2020/12/3
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseResult {
    /**
     * code为返回的状态码
     */
    private Integer code;
    /**
     * msg为后端给前端返回的消息
     */
    private String msg;
    /**
     *data为返回的数据（可以为空）
     */
        private Object data;
}
