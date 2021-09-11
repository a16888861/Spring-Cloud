package com.lucky.kali.consumer.auth;

import com.lucky.kali.common.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * 初始化数据类
 * @author Elliot
 * @date 2021-06-01 19:35
 */
@Component
@Slf4j
public class InitAuth {
    /**
     * 启动时自动加载
     */
    @PostConstruct
    public void init(){
        log.info("JwtAuth:加载Jwt初始化数据认证~");
        JwtUtil.init();
    }
}
