package com.lucky.kali.userinfo.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.lucky.kali.common.base.BaseEntity;
import com.lucky.kali.common.base.BaseServiceImpl;
import com.lucky.kali.common.dto.GroupDTO;
import com.lucky.kali.common.dto.RoleDTO;
import com.lucky.kali.userinfo.dto.GroupRoleDTO;
import com.lucky.kali.userinfo.entity.GroupRole;
import com.lucky.kali.userinfo.mapper.GroupRoleMapper;
import com.lucky.kali.userinfo.service.GroupRoleService;
import com.lucky.kali.userinfo.service.GroupService;
import com.lucky.kali.userinfo.service.RoleService;
import com.lucky.kali.userinfo.vo.req.GroupVO;
import com.lucky.kali.userinfo.vo.resp.GroupRoleVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;

/**
 * 系统-组别角色表 服务实现类
 *
 * @author Elliot
 * @since 2021-08-29
 */
@Service("groupRoleServiceImpl")
public class GroupRoleServiceImpl extends BaseServiceImpl<GroupRoleMapper, GroupRole, GroupRoleDTO> implements GroupRoleService {

    @Resource
    private GroupService groupService;
    @Resource
    private RoleService roleService;

    /**
     * 查询组别角色对应关系List
     *
     * @param groupId 组别ID
     * @return 结果
     */
    @Override
    public GroupRoleVO selectGroupRoleList(String groupId) {
        GroupRoleVO groupRoleVO = new GroupRoleVO();

        GroupDTO groupDTO = groupService.selectById(groupId);
        groupRoleVO.setGroupVO(BeanUtil.copyProperties(groupDTO, GroupVO.class));

        List<GroupRoleDTO> groupRoleDTOList = selectList(new LambdaQueryWrapper<GroupRole>()
                .eq(BaseEntity::getDelFlag, BaseEntity.DEL_FLAG_NORMAL)
                .eq(GroupRole::getGroupId, groupId)
        );

        List<RoleDTO> roleDTOList = new LinkedList<>();
        groupRoleDTOList.parallelStream().forEach(dto -> {
            RoleDTO roleDTO = roleService.selectById(dto.getRoleId());
            roleDTOList.add(roleDTO);
        });

        groupRoleVO.setRoleList(roleDTOList);
        return groupRoleVO;
    }
}
