package com.lucky.kali.consumer.service.impl;

import com.alibaba.nacos.common.utils.UuidUtils;
import com.lucky.kali.common.base.BaseDTO;
import com.lucky.kali.common.base.BaseServiceImpl;
import com.lucky.kali.consumer.dto.UserDTO;
import com.lucky.kali.consumer.entity.User;
import com.lucky.kali.consumer.mapper.UserMapper;
import com.lucky.kali.consumer.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * 用户表 服务实现类
 *
 * @author Elliot
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<UserMapper, User, UserDTO> implements UserService {
    @Resource
    private UserService userService;

    /**
     * 创建用户
     *
     * @param userDTO 用户信息
     * @return 创建结果
     */
    @Override
    public int createUser(UserDTO userDTO) {
        userDTO.setYear(String.valueOf(LocalDateTime.now().getYear()));
        userDTO.setId(UuidUtils.generateUuid()).setCreateDate(new Date()).setDelFlag(BaseDTO.DEL_FLAG_NORMAL);
        int insert = userService.insert(userDTO);
        if (insert > 0){
            return insert;
        }
        return -1;
    }
}
