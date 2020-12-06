package com.southwind.vlog.service;

import com.southwind.vlog.model.dto.LoginDto;
import com.southwind.vlog.model.enity.User;

/**
 * @ClassName UserService用户service接口
 * @Description TODO
 * @Author 86139
 * @Date 2020/12/5
 **/

public interface UserService {
    /**
     * 登录
     * @param loginDto 登录dto对象
     * @return boolean
     */
    boolean login(LoginDto loginDto);

    /**
     * 根据手机号查找用户
     * @param phone
     * @return
     */
    User getUser(String phone);

}
