package com.lucaky.kali.oauth;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author Elliot
 * @date 2021-06-11 21:25
 * TODO 认证服务待完成
 */
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = "com.lucky.kali")
@MapperScan(basePackages = {"com/lucky/kali/business/mapper"})
public class OAuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(OAuthApplication.class, args);
    }
}
