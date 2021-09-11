package com.lucky.kali.userInfo.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 用户处理服务层
 *
 * @author Elliot
 */
@Service
public class UserHandlerService {

    private Map<String, UserHandler> userHandlerMap;

    @Autowired
    public void setOrderHandleMap(List<UserHandler> userHandlerList) {
        // 注入各种类型的用户处理类
        userHandlerMap = userHandlerList.stream().collect(
                Collectors.toMap(orderHandler -> Objects.requireNonNull(AnnotationUtils.findAnnotation(orderHandler.getClass(), UserHandlerType.class)).source(),
                        v -> v, (v1, v2) -> v1));
    }

    public void userService(UserBO userBO) {
        // ...一些前置处理

        // 通过用户来源确定对应的handler
        UserHandler userHandler = userHandlerMap.get(userBO.getSource());
        userHandler.handle(userBO);

        // ...一些后置处理
    }
}
