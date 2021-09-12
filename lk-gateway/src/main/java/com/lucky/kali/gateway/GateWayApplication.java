package com.lucky.kali.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Elliot
 * @date 2021-06-01 21:46
 */
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = "com.lucky.kali")
@ComponentScan(basePackages={"com.lucky.kali"})
public class GateWayApplication {
    public static void main(String[] args) {
        SpringApplication.run(GateWayApplication.class, args);
    }
}
