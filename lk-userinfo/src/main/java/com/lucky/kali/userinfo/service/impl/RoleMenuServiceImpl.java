package com.lucky.kali.userinfo.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.alibaba.csp.sentinel.util.StringUtil;
import com.lucky.kali.common.base.BaseServiceImpl;
import com.lucky.kali.userinfo.dto.RoleMenuDTO;
import com.lucky.kali.userinfo.entity.RoleMenu;
import com.lucky.kali.userinfo.mapper.RoleMenuMapper;
import com.lucky.kali.userinfo.service.RoleMenuService;
import com.lucky.kali.userinfo.vo.req.RoleMenuVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 系统-角色菜单表 服务实现类
 *
 * @author Elliot
 * @since 2021-09-20
 */
@Slf4j
@Service("roleMenuService")
@Transactional(rollbackFor = Exception.class)
public class RoleMenuServiceImpl extends BaseServiceImpl<RoleMenuMapper, RoleMenu, RoleMenuDTO> implements RoleMenuService {

    /**
     * 创建角色菜单(支持单个和多个分配)
     *
     * @param roleMenuVO 角色ID和菜单ID实体
     * @throws Exception 异常
     */
    @Override
    @Async("asyncServiceExecutor")
    public void createRoleMenu(RoleMenuVO roleMenuVO) throws Exception {
        try {
            if (StringUtil.isNotBlank(roleMenuVO.getRoleId()) && ObjectUtil.isNotNull(roleMenuVO.getMenuIdList())) {
                roleMenuVO.getMenuIdList().forEach(menuId -> {
                    RoleMenuDTO roleMenuDTO = RoleMenuDTO.builder()
                            .roleId(roleMenuVO.getRoleId()).menuId(menuId).createBy(roleMenuVO.getUserId()).build();
                    insert(roleMenuDTO);
                });
            }
        } catch (Exception e) {
            log.error("创建角色菜单执行操作时发生错误" + e);
        }
    }
}
