package com.lucky.kali.business.service;

import com.lucky.kali.business.dto.GroupDTO;
import com.lucky.kali.business.entity.Group;
import com.lucky.kali.common.base.BaseService;

/**
 * 组别表 服务类
 *
 * @author Elliot
 */
public interface GroupService extends BaseService<Group, GroupDTO> {

    /**
     * 创建组别信息
     *
     * @param groupDTO 组别信息
     * @return 创建结果
     */
    int createGroup(GroupDTO groupDTO);

}
