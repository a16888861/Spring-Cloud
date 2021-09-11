package com.lucky.kali.userInfo.service;

import com.lucky.kali.common.base.BaseService;
import com.lucky.kali.userInfo.dto.GroupRoleDTO;
import com.lucky.kali.userInfo.entity.GroupRole;
import com.lucky.kali.userInfo.vo.resp.GroupRoleVO;

/**
 * 系统-组别角色表 服务类
 *
 * @author Elliot
 * @since 2021-08-29
 */
public interface GroupRoleService extends BaseService<GroupRole, GroupRoleDTO> {

    /**
     * 查询组别角色对应关系List
     *
     * @param groupId 组别ID
     * @return 结果
     */
    GroupRoleVO selectGroupRoleList(String groupId);
}
