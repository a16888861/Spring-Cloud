package com.lucky.kali.business.service.impl;

import com.lucky.kali.business.dto.RoleDTO;
import com.lucky.kali.business.entity.Role;
import com.lucky.kali.business.mapper.RoleMapper;
import com.lucky.kali.business.service.RoleService;
import com.lucky.kali.common.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 系统-角色表 服务实现类
 *
 * @author Elliot
 */
@Service("roleService")
public class RoleServiceImpl extends BaseServiceImpl<RoleMapper,Role, RoleDTO> implements RoleService {

}
