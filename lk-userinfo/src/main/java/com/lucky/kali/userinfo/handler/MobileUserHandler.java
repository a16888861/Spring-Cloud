package com.lucky.kali.userinfo.handler;

import lombok.extern.slf4j.Slf4j;

/**
 * 用户处理Handler实现
 *
 * @author Elliot
 */
@UserHandlerType(source = "mobile")
@Slf4j
public class MobileUserHandler implements UserHandler {
    @Override
    public void handle(UserBO userBO) {
        log.info("传来的实体信息" + userBO.toString());
    }
}
