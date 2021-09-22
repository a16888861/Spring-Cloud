package com.lucky.kali.userinfo.service;

import com.lucky.kali.common.base.BaseService;
import com.lucky.kali.userinfo.dto.RoleMenuDTO;
import com.lucky.kali.userinfo.entity.RoleMenu;
import com.lucky.kali.userinfo.vo.req.RoleMenuVO;

/**
 * 系统-角色菜单表 服务类
 *
 * @author Elliot
 * @since 2021-09-20
 */
public interface RoleMenuService extends BaseService<RoleMenu, RoleMenuDTO> {

    /**
     * 创建角色菜单(支持单个和多个分配)
     *
     * @param roleMenuVO 角色ID和菜单ID实体
     * @throws Exception 异常
     */
    void createRoleMenu(RoleMenuVO roleMenuVO) throws Exception ;

}
