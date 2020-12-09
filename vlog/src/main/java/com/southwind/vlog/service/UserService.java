package com.southwind.vlog.service;

import com.southwind.vlog.model.dto.LoginDto;
import com.southwind.vlog.model.dto.PhoneLoginDto;
import com.southwind.vlog.model.dto.WxLoginDto;
import com.southwind.vlog.model.enity.User;
import org.springframework.web.multipart.MultipartFile;

/**
 * @ClassName UserService用户service接口
 * @Description TODO
 * @Author 86139
 * @Date 2020/12/5
 **/

public interface UserService {

    /**
     * 登录
     *
     * @param loginDto 登录dto 对象
     * @return boolean
     */
    boolean login(LoginDto loginDto);

    /***
     *
     * @param phone 手机号
     * @return User
     */
    User getUser(String phone);

    /**
     *
     * @param phoneLoginDto 入参
     * @return boolean
     */
    boolean phoneLogin(PhoneLoginDto phoneLoginDto);

    /**
     *修改
     * @param user 用户
     * @return 修改的用户
     */
    User updateUser(User user);

    /**
     * 上传文件到oss
     * @param file 文件对象
     * @return上传后的url
     */
    String uploadFile(MultipartFile file);

    /**
     * 微信登录
     * @param wxLoginDto
     * @return
     */
    User wxLogin(WxLoginDto wxLoginDto);
}