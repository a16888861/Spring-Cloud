package com.lucky.kali.business.handler;

import org.springframework.stereotype.Service;

import java.lang.annotation.*;

/**
 * 自定义用户处理注解
 *
 * @author Elliot
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Service
public @interface UserHandlerType {
    String source();
}
