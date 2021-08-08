package com.lucky.kali.consumer.service.impl;

import com.alibaba.csp.sentinel.util.StringUtil;
import com.lucky.kali.common.base.BaseServiceImpl;
import com.lucky.kali.common.enums.GroupEnums;
import com.lucky.kali.common.util.Md5Utils;
import com.lucky.kali.consumer.dto.UserDTO;
import com.lucky.kali.consumer.entity.User;
import com.lucky.kali.consumer.mapper.UserMapper;
import com.lucky.kali.consumer.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * 用户表 服务实现类
 *
 * @author Elliot
 */
@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
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
        //TODO 创建者待添加 -> 完了写一个 上下文类 从服务上下文中获取创建者ID
        userDTO.setCreateBy("admin");
        if (StringUtil.isBlank(userDTO.getPassword())){
            //如果没有填写密码，则设置初始密码为111111
            userDTO.setPassword(Md5Utils.md5Hex("111111"));
        }else {
            userDTO.setPassword(Md5Utils.md5Hex(userDTO.getPassword()));
        }
        userDTO.setUserGroup(GroupEnums.getGroupCode(userDTO.getUserGroup()));
        userDTO.setYear(String.valueOf(LocalDateTime.now().getYear()));
        int insert = userService.insert(userDTO);
        if (insert > 0){
            return insert;
        }
        return -1;
    }
}
