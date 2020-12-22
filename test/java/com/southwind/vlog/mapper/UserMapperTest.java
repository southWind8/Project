package com.southwind.vlog.mapper;

import com.southwind.vlog.VlogApplication;
import com.southwind.vlog.common.Gender;
import com.southwind.vlog.model.enity.User;
import com.southwind.vlog.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = VlogApplication.class)
@Slf4j
class UserMapperTest {
    @Resource
    private UserMapper userMapper;

    @Test
    void insert() throws Exception {
        User user = User.builder()
                .phone("13961870684")
                .password("a794359756")
                .nickname("南风")
                .avatar("1.jpg")
                .gender(Gender.male.type)
                .birthday(LocalDate.now())
                .address("江苏无锡")
                .createTime(LocalDateTime.now())
                .build();
        userMapper.insert(user);

    }

    @Test
    void findUserByPhone() throws SQLException {
        User user = userMapper.findUserByPhone("13961870684");
        log.info(String.valueOf(user));
    }
    @Test
    void updataUser() throws Exception{
        User user=userMapper.findUserByPhone("13961870684");
        user.setPassword("a794359756");
        user.setNickname("南风");
        user.setAvatar("https://southwind8.oss-cn-hangzhou.aliyuncs.com/%E9%A1%B9%E7%9B%AE/1.jpg");
        user.setGender(Gender.male.type);
        user.setBirthday(LocalDate.of(2000,8,07));
        user.setAddress("江苏无锡滨湖区");
        userMapper.updateUser(user);
    }
}