package com.southwind.vlog.controller;
import com.southwind.vlog.common.ResponseResult;
import com.southwind.vlog.common.ResultCode;
import com.southwind.vlog.model.dto.LoginDto;
import com.southwind.vlog.service.RedisService;
import com.southwind.vlog.service.UserService;
import com.southwind.vlog.utils.SmsUtil;
import com.southwind.vlog.utils.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @ClassName UserController
 * @Description TODO
 * @Author 86139
 * @Date 2020/12/5
 **/
@RestController
@RequestMapping(value = "/api/user")
@Slf4j
public class UserController {
    @Resource
    private UserService userService;
    @Resource
    private SmsUtil smsUtil;
    @Resource
    private RedisService redisService;
    @PostMapping(value = "/login")
    ResponseResult login(@RequestBody LoginDto loginDto){
        log.info("loginDto:"+loginDto);
        boolean flag=userService.login(loginDto);
        if(flag){
            return ResponseResult.success(userService.getUser(loginDto.getPhone()));
        }
        else {
            return ResponseResult.failure(ResultCode.USER_SIGN_IN_FAIL);
        }
    }
    @PostMapping(value = "/sendcode")
    public ResponseResult sendCode(@RequestParam String phone){
        //随机验证码
        String code= StringUtil.getVerifyCode();
        System.out.println(code);
        //给入参手机号发送短信
        boolean flag=smsUtil.sendSms(phone,code);
        if(flag){
            //验证码存在redis,1分钟内有效
            redisService.set(phone,code,1L);
            return ResponseResult.success(code);
        }else {
            return ResponseResult.failure(ResultCode.SMS_ERROR);
        }
    }

}
