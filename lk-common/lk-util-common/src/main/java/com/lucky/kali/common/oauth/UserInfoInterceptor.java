package com.lucky.kali.common.oauth;

import com.alibaba.csp.sentinel.util.StringUtil;
import com.lucky.kali.common.exception.BaseException;
import com.lucky.kali.common.response.Response;
import com.lucky.kali.common.response.ResponseEnum;
import com.lucky.kali.common.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 用户信息拦截器，配合视图拦截器使用
 *
 * @author Elliot
 */
@Component
@Slf4j
public class UserInfoInterceptor implements HandlerInterceptor {
    /**
     * 请求前拦截
     *
     * @param request  请求
     * @param response 响应
     * @param handler  处理
     * @return 结果
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        /*获取请求头授权信息(也就是token)*/
        String authorization = request.getHeader("Authorization");
        log.info("Authorization:" + authorization);
        /*token为空，请求非法*/
        if (StringUtil.isBlank(authorization)){
            throw new BaseException(Response.fail(ResponseEnum.ILLEGAL.getMessage()).getMessage());
        }
        /*校验token信息*/
        if (JwtUtil.verify(authorization)){
            throw new BaseException(Response.fail(ResponseEnum.FAILURE.getMessage()).getMessage());
        }
        /*解析token信息*/
        JwtUtil.getClaim(authorization);
        return Boolean.TRUE;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
