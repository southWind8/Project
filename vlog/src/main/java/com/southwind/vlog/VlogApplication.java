package com.southwind.vlog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

/**
 * @author 86139
 */
@SpringBootApplication
@MapperScan("com.southwind.vlog.mapper")
public class VlogApplication {


    public static void main(String[] args) {
                SpringApplication.run(VlogApplication.class, args);
    }

}
