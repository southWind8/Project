package com.southwind.vlog.controller;

import com.southwind.vlog.common.ResponseResult;
import com.southwind.vlog.common.ResultCode;
import com.southwind.vlog.model.Card;
import com.southwind.vlog.utils.DataUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
/**
 * @ClassName CardController 卡片控制器
 * @Description TODO
 * @Author 86139
 * @Date 2020/12/3
 **/
@RestController
@RequestMapping(value = "api")
public class CardController {

    @GetMapping(value = "cards")
    public ResponseResult getCards(){
        List<Card> cards= DataUtil.initCards();
        /**
         * 枚举的用法
         * 把结果统一给封装起来了
         */
        ResultCode success=ResultCode.SUCCESS;
        return ResponseResult.builder()
                .code(success.code())
                .msg(success.message())
                .data(cards)
                .build();
    }
}
