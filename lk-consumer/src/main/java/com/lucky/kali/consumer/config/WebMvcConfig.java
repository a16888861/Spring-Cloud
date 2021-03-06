package com.lucky.kali.consumer.config;

import com.lucky.kali.common.jwt.UserInfoInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Elliot
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Resource
    private UserInfoInterceptor userInfoInterceptor;

    /**
     * 注册用户信息拦截器
     *
     * @param registry 注册用户
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        List<String> excludePathList = new ArrayList<>();
        excludePathList.add("/");
        excludePathList.add("/doc.html");
        excludePathList.add("/error");
        excludePathList.add("/favicon.ico");
        excludePathList.add("/webjars/**");
        excludePathList.add("/swagger-resources");
        excludePathList.add("/v2/**");
        /*自定义拦截器*/
        InterceptorRegistration interceptorRegistration = registry.addInterceptor(userInfoInterceptor);
        interceptorRegistration.addPathPatterns("/**");
        interceptorRegistration.excludePathPatterns(excludePathList);
    }
}