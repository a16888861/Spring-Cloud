package com.lucky.kali.userinfo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.lucky.kali.common.base.BaseEntity;
import com.lucky.kali.common.base.BaseServiceImpl;
import com.lucky.kali.common.constants.CommonConstants;
import com.lucky.kali.common.context.UserContextUtil;
import com.lucky.kali.common.enums.MenuEnums;
import com.lucky.kali.common.util.BeanUtil;
import com.lucky.kali.userinfo.dto.MenuDTO;
import com.lucky.kali.userinfo.entity.Menu;
import com.lucky.kali.userinfo.mapper.MenuMapper;
import com.lucky.kali.userinfo.service.MenuService;
import com.lucky.kali.userinfo.vo.req.MenuVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * 系统-菜单表 服务实现类
 *
 * @author Elliot
 */
@Slf4j
@Service("menuService")
public class MenuServiceImpl extends BaseServiceImpl<MenuMapper, Menu, MenuDTO> implements MenuService {

    /**
     * 创建菜单
     *
     * @param menuVO 菜单信息实体
     * @return 创建结果
     */
    @Override
    public Integer createMenu(MenuVO menuVO) {
        log.info("创建菜单信息为：" + menuVO.toString());
        menuVO.setIsShow(MenuEnums.getMenuCode(menuVO.getIsShow()))
                .setStatus(MenuEnums.getMenuCode(menuVO.getStatus()));
        MenuDTO menuDTO = BeanUtil.copyProperties(menuVO, MenuDTO.class);
        Objects.requireNonNull(menuDTO).setCreateBy(UserContextUtil.getUserInfo().getId());
        return insert(menuDTO);
    }

    /**
     * 查询菜单集合(用于给角色分配权限时用)
     * 传'top'查询顶级菜单信息
     * 传'null'默认查询一级菜单集合
     * 传父级id则根据父级ID查询子级菜单集合
     *
     * @param parentId 查询参数
     * @return 查询结果
     */
    @Override
    public List<MenuDTO> selectMenuList(String parentId) {
        /* 查询菜单集合
         * 根据字段内容去选择查询
         * 默认根据父级ID查询子级菜单集合*/
        List<MenuDTO> menuDTOList;
        switch (parentId) {
            /*如果是top则查询顶级菜单信息*/
            case CommonConstants.SELECT_TOP:
                menuDTOList = selectList(new LambdaQueryWrapper<Menu>()
                        .eq(Menu::getDelFlag, BaseEntity.DEL_FLAG_NORMAL).eq(Menu::getType, BaseEntity.DEL_FLAG_NORMAL));
                break;
            /*如果是null则查询一级菜单信息*/
            case CommonConstants.SELECT_NULL:
                menuDTOList = selectList(new LambdaQueryWrapper<Menu>()
                        .eq(Menu::getDelFlag, BaseEntity.DEL_FLAG_NORMAL).eq(Menu::getType, BaseEntity.DEL_FLAG_DELETE));
                break;
            /*默认根据父级ID查询子级菜单集合*/
            default:
                menuDTOList = selectList(new LambdaQueryWrapper<Menu>()
                        .eq(Menu::getDelFlag, BaseEntity.DEL_FLAG_NORMAL).eq(Menu::getType, parentId));
                break;
        }
        return menuDTOList;
    }
}
