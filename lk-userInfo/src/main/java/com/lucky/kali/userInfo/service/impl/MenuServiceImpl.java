package com.lucky.kali.userInfo.service.impl;

import com.lucky.kali.common.base.BaseServiceImpl;
import com.lucky.kali.userInfo.dto.MenuDTO;
import com.lucky.kali.userInfo.entity.Menu;
import com.lucky.kali.userInfo.mapper.MenuMapper;
import com.lucky.kali.userInfo.service.MenuService;
import org.springframework.stereotype.Service;

/**
 * 系统-菜单表 服务实现类
 *
 * @author Elliot
 */
@Service("menuService")
public class MenuServiceImpl extends BaseServiceImpl<MenuMapper, Menu, MenuDTO> implements MenuService {

}
