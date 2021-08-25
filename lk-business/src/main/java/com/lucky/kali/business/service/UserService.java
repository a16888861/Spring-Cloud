package com.lucky.kali.business.service;

import com.lucky.kali.business.dto.UserDTO;
import com.lucky.kali.business.entity.User;
import com.lucky.kali.business.vo.req.UserVOPage;
import com.lucky.kali.common.base.BaseService;
import com.lucky.kali.common.util.CommonPage;

/**
 * 用户表 服务类
 *
 * @author Elliot
 */
public interface UserService extends BaseService<User, UserDTO> {
    /**
     * 创建用户
     *
     * @param userDTO 用户信息
     * @return 创建结果
     */
    int createUser(UserDTO userDTO);

    /**
     * 查询用户分页信息
     *
     * @param userVoPage 查询条件
     * @return 查询结果
     */
    CommonPage<UserDTO> selectUserPageList(UserVOPage userVoPage);

}
