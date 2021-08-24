package com.lucky.kali.business.service.impl;

import com.lucky.kali.business.dto.MenuDTO;
import com.lucky.kali.business.entity.Menu;
import com.lucky.kali.business.mapper.MenuMapper;
import com.lucky.kali.business.service.MenuService;
import com.lucky.kali.common.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 系统-菜单表 服务实现类
 *
 * @author Elliot
 */
@Service("menuService")
public class MenuServiceImpl extends BaseServiceImpl<MenuMapper, Menu, MenuDTO> implements MenuService {

}
