package com.southwind.vlog.config;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * @ClassName CaptchaConfig
 * @Description TODO
 * @Author 南风
 * @Date 2020/12/10
 **/
@Configuration
public class CaptchaConfig {
    @Bean
    public DefaultKaptcha defaultKaptcha(){
        DefaultKaptcha defaultKaptcha=new DefaultKaptcha();
        Properties properties=new Properties();
        //验证码长度
        properties.setProperty("kaptcha.textproducer.char.length","4");
        //有边框
        properties.setProperty("kaptcha.border","yes");
        //边框色
        properties.setProperty("kaptcha.border.color","168,184,204");
        //字体颜色
        properties.setProperty("kaptcha.textproducer.font.color","88,82,135");
        //渐变色
        properties.setProperty("kaptcha.background.clear.from","197,150,212");
        properties.setProperty("kaptcha.background.clear.to","76,124,94");
        //字体大小
        properties.setProperty("keptcha.textproducer.font.size","30");
        //字体
        properties.setProperty("kaptcha.textproducer.font.names","微软雅黑");
        //图片验证码大小
        properties.setProperty("keptcha.image.width","120");
        properties.setProperty("kaptcha.image.height","45");
        //验证码保存的key
        properties.setProperty("kapcha.session.key","code");
        Config config=new Config(properties);
        defaultKaptcha.setConfig(config);
        return defaultKaptcha;

    }
}
