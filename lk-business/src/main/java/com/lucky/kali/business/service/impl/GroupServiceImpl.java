package com.lucky.kali.business.service.impl;

import com.lucky.kali.business.dto.GroupDTO;
import com.lucky.kali.business.entity.Group;
import com.lucky.kali.business.mapper.GroupMapper;
import com.lucky.kali.business.service.GroupService;
import com.lucky.kali.common.base.BaseServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 组别表 服务实现类
 *
 * @author Elliot
 */
@Slf4j
@Service("groupService")
@Transactional(rollbackFor = Exception.class)
public class GroupServiceImpl extends BaseServiceImpl<GroupMapper, Group, GroupDTO> implements GroupService {

    @Resource
    @Qualifier("groupService")
    private GroupService groupService;

    /**
     * 创建组别信息
     *
     * @param groupDTO 组别信息
     * @return 创建结果
     */
    @Override
    public int createGroup(GroupDTO groupDTO) {
        int insert = groupService.insert(groupDTO);
        if (insert > 0){
            return insert;
        }
        return 0;
    }
}
