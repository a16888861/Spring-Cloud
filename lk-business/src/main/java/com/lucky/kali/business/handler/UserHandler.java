package com.lucky.kali.business.handler;

/**
 * 用户处理Handler接口
 *
 * @author Elliot
 */
public interface UserHandler {
    /**
     * 自定义用户处理类
     *
     * @param userBO 用户信息
     */
    void handle(UserBO userBO);
}
