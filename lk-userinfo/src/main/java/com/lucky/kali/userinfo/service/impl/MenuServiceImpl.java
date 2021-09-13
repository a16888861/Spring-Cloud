package com.lucky.kali.userinfo.service.impl;

import com.lucky.kali.common.base.BaseServiceImpl;
import com.lucky.kali.userinfo.dto.MenuDTO;
import com.lucky.kali.userinfo.entity.Menu;
import com.lucky.kali.userinfo.mapper.MenuMapper;
import com.lucky.kali.userinfo.service.MenuService;
import org.springframework.stereotype.Service;

/**
 * 系统-菜单表 服务实现类
 *
 * @author Elliot
 */
@Service("menuService")
public class MenuServiceImpl extends BaseServiceImpl<MenuMapper, Menu, MenuDTO> implements MenuService {

}
