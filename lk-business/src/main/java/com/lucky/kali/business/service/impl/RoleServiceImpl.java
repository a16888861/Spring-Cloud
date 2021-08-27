package com.lucky.kali.business.service.impl;

import com.lucky.kali.business.dto.RoleDTO;
import com.lucky.kali.business.entity.Role;
import com.lucky.kali.business.mapper.RoleMapper;
import com.lucky.kali.business.service.RoleService;
import com.lucky.kali.common.base.BaseServiceImpl;
import com.lucky.kali.common.enums.RoleEnums;
import org.springframework.stereotype.Service;

/**
 * 系统-角色表 服务实现类
 *
 * @author Elliot
 */
@Service("roleService")
public class RoleServiceImpl extends BaseServiceImpl<RoleMapper, Role, RoleDTO> implements RoleService {

    /**
     * 创建角色
     *
     * @param roleDTO 角色信息
     * @return 创建结果
     */
    @Override
    public int createRole(RoleDTO roleDTO) {
        //TODO 创建者待添加
        roleDTO.setCreateBy("1430109634181881856");

        roleDTO.setStatus(RoleEnums.getRoleCodeByRoleEnName(roleDTO.getStatus()));
        int insert = insert(roleDTO);
        if (insert > 0) {
            return insert;
        }
        return -1;
    }
}
