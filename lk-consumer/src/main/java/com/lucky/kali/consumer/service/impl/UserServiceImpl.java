package com.lucky.kali.consumer.service.impl;

import com.alibaba.nacos.common.utils.UuidUtils;
import com.lucky.kali.common.base.BaseDTO;
import com.lucky.kali.common.base.BaseServiceImpl;
import com.lucky.kali.common.enums.GroupEnums;
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
        userDTO.setId(UuidUtils.generateUuid())
                //TODO 创建者待添加 -> 完了写一个 上下文类 从服务上下文中获取创建者ID
                .setCreateBy("")
                .setCreateDate(new Date())
                .setDelFlag(BaseDTO.DEL_FLAG_NORMAL);
        userDTO.setGroup(GroupEnums.getGroupCode(userDTO.getGroup()));
        userDTO.setYear(String.valueOf(LocalDateTime.now().getYear()));
        int insert = userService.insert(userDTO);
        if (insert > 0){
            return insert;
        }
        return -1;
    }
}
