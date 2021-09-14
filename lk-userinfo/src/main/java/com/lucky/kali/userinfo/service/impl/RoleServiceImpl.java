package com.lucky.kali.userinfo.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.csp.sentinel.util.StringUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lucky.kali.common.base.BaseEntity;
import com.lucky.kali.common.base.BaseServiceImpl;
import com.lucky.kali.common.base.CommonPage;
import com.lucky.kali.common.context.UserContextUtil;
import com.lucky.kali.common.dto.RoleDTO;
import com.lucky.kali.common.enums.RoleEnums;
import com.lucky.kali.common.util.PageUtil;
import com.lucky.kali.userinfo.dto.GroupRoleDTO;
import com.lucky.kali.userinfo.entity.Role;
import com.lucky.kali.userinfo.mapper.RoleMapper;
import com.lucky.kali.userinfo.service.GroupRoleService;
import com.lucky.kali.userinfo.service.RoleService;
import com.lucky.kali.userinfo.vo.req.RoleVO;
import com.lucky.kali.userinfo.vo.req.RoleVOPage;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 系统-角色表 服务实现类
 *
 * @author Elliot
 */
@Service("roleService")
public class RoleServiceImpl extends BaseServiceImpl<RoleMapper, Role, RoleDTO> implements RoleService {

    @Resource
    private GroupRoleService groupRoleService;

    /**
     * 创建角色
     *
     * @param roleVO 角色信息
     * @return 创建结果
     */
    @Override
    public int createRole(RoleVO roleVO) {
        RoleDTO roleDTO = BeanUtil.copyProperties(roleVO, RoleDTO.class);
        roleDTO.setCreateBy(UserContextUtil.getUserInfo().getId());
        roleDTO.setStatus(RoleEnums.getRoleCodeByRoleEnName(roleDTO.getStatus()));
        int roleInsert = insert(roleDTO);

        /*组别角色表信息插入，角色和组别对应*/
        GroupRoleDTO groupRoleDTO = GroupRoleDTO.builder()
                .groupId(roleVO.getGroupId())
                .roleId(roleDTO.getId())
                .createBy(UserContextUtil.getUserInfo().getId())
                .build();
        int groupRoleInsert = groupRoleService.insert(groupRoleDTO);
        if (roleInsert == groupRoleInsert) {
            return groupRoleInsert;
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
