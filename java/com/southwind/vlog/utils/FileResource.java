package com.southwind.vlog.utils;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @ClassName FileResource文件配置类
 * @Description TODO
 * @Author 南风
 * @Date 2020/12/7
 **/
@Data
@Component
@PropertySource("classpath:file.properties")
@ConfigurationProperties(prefix="file")
public class FileResource {
    private String host;
    private  String endpoint;
    private  String bucketName;
    private  String objectName;
    private  String ossHost;

}
