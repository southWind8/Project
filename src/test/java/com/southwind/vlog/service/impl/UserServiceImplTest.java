package com.southwind.vlog.service.impl;
import com.southwind.vlog.VlogApplication;
import com.southwind.vlog.model.dto.LoginDto;
import com.southwind.vlog.model.enity.User;
import com.southwind.vlog.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest(classes = VlogApplication.class)
@Slf4j
public class UserServiceImplTest {
    @Resource
    private UserService userService;
    @Test
    public void login() {
        LoginDto loginDto=LoginDto.builder()
                .phone("13961870684")
                .password("a794359756")
                .build();
        boolean flag=userService.login(loginDto);
        assertTrue(flag);
    }

    @Test
    public void getUser() {
        User user=userService.getUser("13961870684");
        log.info(String.valueOf(user));
    }
}