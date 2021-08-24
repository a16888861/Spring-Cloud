package com.lucky.kali.business.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lucky.kali.business.dto.GroupDTO;
import com.lucky.kali.business.entity.Group;
import com.lucky.kali.business.mapper.GroupMapper;
import com.lucky.kali.business.service.GroupService;
import com.lucky.kali.business.vo.req.GroupVO;
import com.lucky.kali.common.base.BaseEntity;
import com.lucky.kali.common.base.BaseServiceImpl;
import com.lucky.kali.common.util.PageUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * 组别表 服务实现类
 *
 * @author Elliot
 */
@Slf4j
@Service("groupService")
@Transactional(rollbackFor = Exception.class)
public class GroupServiceImpl extends BaseServiceImpl<GroupMapper, Group, GroupDTO> implements GroupService {

    /**
     * 创建组别信息
     *
     * @param groupDTO 组别信息
     * @return 创建结果
     */
    @Override
    public int createGroup(GroupDTO groupDTO) {
        groupDTO.setYear(String.valueOf(LocalDateTime.now().getYear()));
        int insert = insert(groupDTO);
        if (insert > 0) {
            return insert;
        }
        return -1;
    }

    /**
     * 查询组别分页信息
     *
     * @param pageCurrent 查询页数
     * @param pageSize    每页数量
     * @return 查询结果
     */
    @Override
    public Page<GroupVO> selectGroupPageList(int pageCurrent, int pageSize) {
        Page<Group> page1 = new Page<>();
        Page<GroupVO> voPage = new Page<>();
        Page<GroupDTO> page = new Page<>();
        page.setCurrent(pageCurrent).setSize(pageSize);
        Page<GroupDTO> groupDtoPage = selectPage(page, new LambdaQueryWrapper<Group>().eq(BaseEntity::getDelFlag, BaseEntity.DEL_FLAG_NORMAL));
        voPage.setRecords(PageUtil.copyList(groupDtoPage.getRecords(),GroupVO.class))
                .setSize(groupDtoPage.getSize()).setCurrent(groupDtoPage.getCurrent())
                .setPages(groupDtoPage.getPages()).setTotal(groupDtoPage.getTotal());
        return voPage;
    }
}
