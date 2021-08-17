package com.lucky.kali.consumer;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author Elliot
 * @date 2021-06-01 02:07
 */
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = "com.lucky.kali", exclude = DataSourceAutoConfiguration.class)
@MapperScan(basePackages = {"com.lucky.kali.consumer.mapper"})
public class ConsumerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication.class, args);
    }
}
