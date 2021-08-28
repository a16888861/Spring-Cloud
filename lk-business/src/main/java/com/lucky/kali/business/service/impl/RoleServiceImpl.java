package com.lucky.kali.business.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.csp.sentinel.util.StringUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lucky.kali.business.dto.RoleDTO;
import com.lucky.kali.business.entity.Role;
import com.lucky.kali.business.mapper.RoleMapper;
import com.lucky.kali.business.service.RoleService;
import com.lucky.kali.business.vo.req.RoleVO;
import com.lucky.kali.business.vo.req.RoleVOPage;
import com.lucky.kali.common.base.BaseEntity;
import com.lucky.kali.common.base.BaseServiceImpl;
import com.lucky.kali.common.enums.RoleEnums;
import com.lucky.kali.common.util.CommonPage;
import com.lucky.kali.common.util.PageUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 系统-角色表 服务实现类
 *
 * @author Elliot
 */
@Slf4j
@Service("roleService")
public class RoleServiceImpl extends BaseServiceImpl<RoleMapper, Role, RoleDTO> implements RoleService {

    /**
     * 创建角色
     *
     * @param roleVO 角色信息
     * @return 创建结果
     */
    @Override
    public int createRole(RoleVO roleVO) {
        RoleDTO roleDTO = BeanUtil.copyProperties(roleVO, RoleDTO.class);
        //TODO 创建者待添加
        roleDTO.setCreateBy("1430109634181881856");

        roleDTO.setStatus(RoleEnums.getRoleCodeByRoleEnName(roleDTO.getStatus()));
        int insert = insert(roleDTO);

        //TODO 组别角色表信息插入待完成
        log.info(roleDTO.getId());
        if (insert > 0) {
            return insert;
        }
        return -1;
    }

    /**
     * 查询角色列表
     *
     * @param roleVoPage 查询条件
     * @return 查询结果
     */
    @Override
    public CommonPage<RoleDTO> selectRolePage(RoleVOPage roleVoPage) {
        Page<RoleDTO> page = new Page<>();
        page.setSize(roleVoPage.getPageSize()).setCurrent(roleVoPage.getPageCurrent());
        Page<RoleDTO> roleDtoPage = selectPage(page, new LambdaQueryWrapper<Role>()
                .eq(BaseEntity::getDelFlag, BaseEntity.DEL_FLAG_NORMAL)
                .eq(StringUtil.isNotBlank(roleVoPage.getStatus()), Role::getStatus, RoleEnums.getRoleCodeByRoleEnName(roleVoPage.getStatus()))
                .like(StringUtil.isNotBlank(roleVoPage.getName()), Role::getName, roleVoPage.getName())
                .like(StringUtil.isNotBlank(roleVoPage.getEnName()), Role::getEnName, roleVoPage.getEnName())
                .like(StringUtil.isNotBlank(roleVoPage.getType()), Role::getType, roleVoPage.getType())
        );
        return PageUtil.transform(roleDtoPage, RoleDTO.class);
    }
}
