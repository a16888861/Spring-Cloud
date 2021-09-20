package com.lucky.kali.userinfo.service.impl;

import com.lucky.kali.common.base.BaseServiceImpl;
import com.lucky.kali.userinfo.dto.RoleMenuDTO;
import com.lucky.kali.userinfo.entity.RoleMenu;
import com.lucky.kali.userinfo.mapper.RoleMenuMapper;
import com.lucky.kali.userinfo.service.RoleMenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 系统-角色菜单表 服务实现类
 *
 * @author Elliot
 * @since 2021-09-20
 */
@Slf4j
@Service("roleMenuService")
public class RoleMenuServiceImpl extends BaseServiceImpl<RoleMenuMapper, RoleMenu, RoleMenuDTO> implements RoleMenuService {

}
