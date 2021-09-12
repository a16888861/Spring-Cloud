package com.lucky.kali.business;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Elliot
 */
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = "com.lucky.kali", exclude = DataSourceAutoConfiguration.class)
@MapperScan(basePackages = {"com/lucky/kali/business/mapper"})
@EnableFeignClients(basePackages = "com.lucky.kali")
@ComponentScan(basePackages={"com.lucky.kali"})
public class BusinessApplication {
    public static void main(String[] args) {
        SpringApplication.run(BusinessApplication.class, args);
    }
}
