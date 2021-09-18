package com.lucky.kali.userinfo.service;

import com.lucky.kali.common.base.BaseService;
import com.lucky.kali.userinfo.dto.MenuDTO;
import com.lucky.kali.userinfo.entity.Menu;
import com.lucky.kali.userinfo.vo.req.MenuVO;

import java.util.List;

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

    /**
     * 查询菜单集合(用于给角色分配权限时用)
     * 传'top'查询顶级菜单信息
     * 传'null'默认查询一级菜单集合
     * 传父级id则根据父级ID查询子级菜单集合
     *
     * @param parentId 查询参数
     * @return 查询结果
     */
    List<MenuDTO> selectMenuList(String parentId);
}
