package com.lucky.kali.business.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author elliot
 * 注解说明
 * Order 优先级调整
 * EnableWebSecurity 开启web保护
 * EnableGlobalMethodSecurity(prePostEnabled = true) 开启方法注解权限配置
 */

@Order(-1)
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

}
