package com.lucky.kali.business;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author Elliot
 * @date 2021-06-01 02:07
 */
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = "com.lucky.kali")
@MapperScan(basePackages = {"com/lucky/kali/business/mapper"})
public class BusinessApplication {
    public static void main(String[] args) {
        SpringApplication.run(BusinessApplication.class, args);
    }
}
