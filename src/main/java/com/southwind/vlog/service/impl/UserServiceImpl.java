package com.southwind.vlog.service.impl;

import com.southwind.vlog.mapper.UserMapper;
import com.southwind.vlog.model.dto.LoginDto;
import com.southwind.vlog.model.enity.User;
import com.southwind.vlog.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.SQLException;

/**
 * @ClassName UserServiceImpl
 * @Description TODO
 * @Author 86139
 * @Date 2020/12/5
 **/
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;
    @Override
    public boolean login(LoginDto loginDto) {
        User user=getUser(loginDto.getPhone());
        if(user!=null){
            //对客户端传递的密码进行加密后和数据库中的密码相比较
            return DigestUtils.md5Hex(loginDto.getPassword()).equals(user.getPassword());
        }
        return false;
    }

    @Override
    public User getUser(String phone) {
        User user=null;
        try {
            user=userMapper.findUserByPhone(phone);
        } catch (SQLException e) {
            System.err.println("根据手机号查找用户出现异常");
        }
        return user;
    }
}
