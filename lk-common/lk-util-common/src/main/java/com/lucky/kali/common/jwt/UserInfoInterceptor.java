package com.lucky.kali.common.jwt;

import com.alibaba.csp.sentinel.util.StringUtil;
import com.lucky.kali.common.constants.CommonConstants;
import com.lucky.kali.common.exception.BaseException;
import com.lucky.kali.common.response.Response;
import com.lucky.kali.common.response.ResponseEnum;
import com.lucky.kali.common.util.JwtUtil;
import com.lucky.kali.common.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * 用户信息拦截器，配合视图拦截器使用
 *
 * @author Elliot
 */
@Component
@Slf4j
public class UserInfoInterceptor implements HandlerInterceptor {

    private final ThreadLocal<Long> THREAD = new ThreadLocal<>();

    @Resource
    private RedisUtil redisUtil;

    /**
     * preHandle 请求前拦截
     * 调用时间：Controller方法处理之前
     * 执行顺序：链式Intercepter情况下，Intercepter按照声明的顺序一个接一个执行
     * 若返回false，则中断执行，注意：不会进入afterCompletion
     *
     * @param request  请求
     * @param response 响应
     * @param handler  处理
     * @return 结果
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        THREAD.set(System.currentTimeMillis());
        /*获取请求头授权信息(也就是token和刷新token)*/
        String authorization = request.getHeader(CommonConstants.AUTHORIZATION);
        String xAuthorization = request.getHeader(CommonConstants.X_AUTHORIZATION);
        log.info("Authorization:" + authorization);
        log.info("X-Authorization:" + xAuthorization);
        /*token为空，请求非法*/
        if (StringUtil.isBlank(authorization) && StringUtil.isBlank(xAuthorization)) {
            throw new BaseException(Response.fail(ResponseEnum.ILLEGAL.getMessage()).getMessage());
        }
        /*解析token信息*/
        String claim = JwtUtil.getClaim(authorization.split(CommonConstants.SPACE)[1]);
        String xClaim = JwtUtil.getClaim(xAuthorization.split(CommonConstants.SPACE)[1]);
        log.info("用户token信息为：" + claim);
        log.info("用户刷新token信息为：" + xClaim);
        /*判断token是否存在(是否过期)*/
        if (!redisUtil.hasKey(claim)) {
            /*判断刷新token是否存在(是否过期) 不存在则判断刷新token*/
            if (redisUtil.hasKey(xClaim)) {
                /*刷新token没有过期，重新对token进行续签操作(续签时间30分钟)*/
                long tokenExpiredTime = TimeUnit.SECONDS.toSeconds(1800);
                redisUtil.set(claim, authorization, tokenExpiredTime);
            } else {
                response.setStatus(ResponseEnum.TOKEN_TIMEOUT.getCode());
                try {
                    /*重定向URL待添加*/
                    response.sendRedirect("Token已过期");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                throw new BaseException(Response.fail(ResponseEnum.TOKEN_TIMEOUT.getMessage()).getMessage());
            }
        }
        /*判断token是否错误*/
        if (!authorization.equals(redisUtil.get(claim)) && !xAuthorization.equals(redisUtil.get(xClaim))) {
            response.setStatus(ResponseEnum.TOKEN_ERROR.getCode());
            try {
                /*重定向URL待添加*/
                response.sendRedirect("Token信息错误");
            } catch (IOException e) {
                e.printStackTrace();
            }
            throw new BaseException(Response.fail(ResponseEnum.TOKEN_ERROR.getMessage()).getMessage());
        }
        return Boolean.TRUE;
    }

    /**
     * postHandle
     * 调用前提：preHandle返回true
     * 调用时间：Controller方法处理完之后，DispatcherServlet进行视图的渲染之前，也就是说在这个方法中你可以对ModelAndView进行操作
     * 执行顺序：链式Intercepter情况下，Intercepter按照声明的顺序倒着执行。
     * 备注：postHandle虽然post打头，但post、get方法都能处理
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("postHandle未做任何处理");
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    /**
     * afterCompletion
     * 调用前提：preHandle返回true
     * 调用时间：DispatcherServlet进行视图的渲染之后
     * 多用于清理资源
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        long millis = System.currentTimeMillis();
        log.info(request.getServletPath() + "耗时：" + (millis - THREAD.get()) + "ms");
        THREAD.remove();
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
