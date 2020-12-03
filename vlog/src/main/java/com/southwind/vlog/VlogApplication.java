package com.southwind.vlog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

/**
 * @author 86139
 */
@SpringBootApplication (exclude = {DataSourceAutoConfiguration.class})

public class VlogApplication {


    public static void main(String[] args) {
                SpringApplication.run(VlogApplication.class, args);
    }

}
