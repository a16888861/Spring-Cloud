package com.lucky.kali.common.response;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * @author Elliot
 */
@Component
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
public class Response {

    @Resource
    private MessageSource messageSource;

    private static Response response;

    @PostConstruct
    public void init() {
        response = this;
    }

    public static <T> ResponseInfo<T> tips(String message, String... args) {
        return returnResult(ResponseEnum.SUCCESS.getCode(), response.messageSource.getMessage(message, args, LocaleContextHolder.getLocale()), null);
    }

    public static <T> ResponseInfo<T> data(T data) {
        return returnResult(ResponseEnum.SUCCESS.getCode(), response.messageSource.getMessage(ResponseEnum.SUCCESS.getMessage(), null, LocaleContextHolder.getLocale()), data);
    }

    public static <T> ResponseInfo<T> data(String message, T data, String... args) {
        return returnResult(ResponseEnum.SUCCESS.getCode(), response.messageSource.getMessage(message, args, LocaleContextHolder.getLocale()), data);
    }

    /**
     * 请求失败
     */
    public static <T> ResponseInfo<T> fail(String message, String... args) {
        return returnResult(ResponseEnum.FAILURE.getCode(), response.messageSource.getMessage(message, args, LocaleContextHolder.getLocale()), null);
    }

    public static <T> ResponseInfo<T> fail(ResponseEnum responseEnum) {
        return returnResult(responseEnum.getCode(), response.messageSource.getMessage(responseEnum.getMessage(), null, LocaleContextHolder.getLocale()), null);
    }

    public static <T> ResponseInfo<T> fail(ResponseEnum responseEnum, String message, String... args) {
        return returnResult(responseEnum.getCode(), response.messageSource.getMessage(message, args, LocaleContextHolder.getLocale()), null);
    }

    public static <T> ResponseInfo<T> fail(ResponseEnum responseEnum, String message, T data, String... args) {
        return returnResult(responseEnum.getCode(), response.messageSource.getMessage(message, args, LocaleContextHolder.getLocale()), data);
    }

    /**
     * 请求成功
     */
    public static <T> ResponseInfo<T> success(String message, String... args) {
        return returnResult(ResponseEnum.SUCCESS.getCode(), response.messageSource.getMessage(message, args, LocaleContextHolder.getLocale()), null);
    }

    public static <T> ResponseInfo<T> success(ResponseEnum responseEnum) {
        return returnResult(ResponseEnum.SUCCESS.getCode(), response.messageSource.getMessage(responseEnum.getMessage(), null, LocaleContextHolder.getLocale()), null);
    }

    public static <T> ResponseInfo<T> success(ResponseEnum responseEnum, String message, String... args) {
        return returnResult(ResponseEnum.SUCCESS.getCode(), response.messageSource.getMessage(message, args, LocaleContextHolder.getLocale()), null);
    }

    public static <T> ResponseInfo<T> success(String message, T data, String... args) {
        return returnResult(ResponseEnum.SUCCESS.getCode(), response.messageSource.getMessage(message, args, LocaleContextHolder.getLocale()), data);
    }

    /**
     * 未找到
     */
    public static <T> ResponseInfo<T> notFound(String message, String... args) {
        return returnResult(ResponseEnum.NOTFOUND.getCode(), response.messageSource.getMessage(message, args, LocaleContextHolder.getLocale()), null);
    }

    public static <T> ResponseInfo<T> notFound(ResponseEnum responseEnum) {
        return returnResult(ResponseEnum.NOTFOUND.getCode(), response.messageSource.getMessage(responseEnum.getMessage(), null, LocaleContextHolder.getLocale()), null);
    }

    public static <T> ResponseInfo<T> notFound(ResponseEnum responseEnum, String message, String... args) {
        return returnResult(ResponseEnum.NOTFOUND.getCode(), response.messageSource.getMessage(message, args, LocaleContextHolder.getLocale()), null);
    }

    public static <T> ResponseInfo<T> notFound(ResponseEnum responseEnum, String message, T data, String... args) {
        return returnResult(ResponseEnum.NOTFOUND.getCode(), response.messageSource.getMessage(message, args, LocaleContextHolder.getLocale()), data);
    }

    /**
     * 自定义
     */
    public static <T> ResponseInfo<T> custom(int code, String message, T data, String... args) {
        return returnResult(code, response.messageSource.getMessage(message, args, LocaleContextHolder.getLocale()), data);
    }

    private static <T> ResponseInfo<T> returnResult(int code, String message, T data) {
        ResponseInfo<T> responseInfo = new ResponseInfo<>();
        responseInfo.setStatusCode(code);
        responseInfo.setMessage(message);
        responseInfo.setData(data);
        return responseInfo;
    }

}
