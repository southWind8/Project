package com.southwind.vlog.utils;

import com.southwind.vlog.VlogApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.Assert.*;
@SpringBootTest(classes = VlogApplication.class)
@Slf4j
public class SmsUtilTest {
@Resource
private SmsUtil smsUtil;
    @Test
    public void sendSms() {
        smsUtil.sendSms("13961870684","123123");
    }
}