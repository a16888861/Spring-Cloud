package com.lucky.kali.userinfo.service.impl;

import com.lucky.kali.common.base.BaseServiceImpl;
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

}
