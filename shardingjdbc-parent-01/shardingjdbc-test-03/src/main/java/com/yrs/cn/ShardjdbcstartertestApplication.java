package com.yrs.cn;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

//@SpringBootApplication
//@Configuration
//@SpringBootConfiguration
@MapperScan("com.yrs.cn.alarmmanager.mapper")
//@EnableSwagger2
@SpringBootApplication
//        (exclude = {
//        SecurityAutoConfiguration.class,
//        DataSourceAutoConfiguration.class
//        ,DataSourceTransactionManagerAutoConfiguration.class
//        ,DruidDataSourceAutoConfigure.class
//        ,HibernateJpaAutoConfiguration.class
//})
@EnableSwagger2
public class ShardjdbcstartertestApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShardjdbcstartertestApplication.class, args);
    }

}
