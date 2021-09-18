package com.lucky.kali.userinfo.service;

import com.lucky.kali.common.base.BaseService;
import com.lucky.kali.userinfo.dto.MenuDTO;
import com.lucky.kali.userinfo.entity.Menu;
import com.lucky.kali.userinfo.vo.req.MenuVO;

/**
 * 系统-菜单表 服务类
 *
 * @author Elliot
 * @since 2021-08-24
 */
public interface MenuService extends BaseService<Menu, MenuDTO> {

    /**
     * 创建菜单
     *
     * @param menuVO 菜单信息实体
     * @return 创建结果
     */
    Integer createMenu(MenuVO menuVO);

}
