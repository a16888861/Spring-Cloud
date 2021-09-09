package com.lucky.kali.common.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 通用的响应
 * @author Elliot
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseInfo<T> implements Serializable {

    /**
     * 状态码
     */
    private int statusCode;

    /**
     * 返回信息
     */
    private String message;

    /**
     * 返回数据
     */
    private T data;

    /**
     * 自定义返回信息
     * @param code      状态码
     * @param message   返回信息
     */
    public ResponseInfo(int code, String message){
        this(code,message,null);
    }
}
