package com.lucky.kali.userInfo.service;

import com.lucky.kali.common.base.BaseService;
import com.lucky.kali.common.base.CommonPage;
import com.lucky.kali.common.dto.RoleDTO;
import com.lucky.kali.userInfo.entity.Role;
import com.lucky.kali.userInfo.vo.req.RoleVO;
import com.lucky.kali.userInfo.vo.req.RoleVOPage;

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
     * @param roleVO 角色信息
     * @return 创建结果
     */
    int createRole(RoleVO roleVO);

    /**
     * 查询角色列表
     *
     * @param roleVoPage 查询条件
     * @return 查询结果
     */
    CommonPage<RoleDTO> selectRolePage(RoleVOPage roleVoPage);
}
