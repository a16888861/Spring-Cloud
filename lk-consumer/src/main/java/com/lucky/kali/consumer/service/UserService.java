package com.lucky.kali.consumer.service;

import com.lucky.kali.common.base.BaseService;
import com.lucky.kali.consumer.dto.UserDTO;
import com.lucky.kali.consumer.entity.User;

/**
 * 用户表 服务类
 *
 * @author Elliot
 */
public interface UserService extends BaseService<User, UserDTO> {
    /**
     * 创建用户
     * @param userDTO       用户信息
     * @return              创建结果
     */
    int createUser(UserDTO userDTO);

}
