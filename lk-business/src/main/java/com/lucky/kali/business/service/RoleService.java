package com.lucky.kali.business.service;

import com.lucky.kali.business.dto.RoleDTO;
import com.lucky.kali.business.entity.Role;
import com.lucky.kali.common.base.BaseService;

/**
 * 系统-角色表 服务类
 *
 * @author Elliot
 * @since 2021-08-24
 */
public interface RoleService extends BaseService<Role, RoleDTO> {

    /**
     * 创建角色
     *
     * @param roleDTO 角色信息
     * @return 创建结果
     */
    int createRole(RoleDTO roleDTO);
}
