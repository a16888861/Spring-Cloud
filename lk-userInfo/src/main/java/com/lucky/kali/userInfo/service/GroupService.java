package com.lucky.kali.userInfo.service;

import com.lucky.kali.common.base.BaseService;
import com.lucky.kali.common.base.CommonPage;
import com.lucky.kali.common.dto.GroupDTO;
import com.lucky.kali.userInfo.entity.Group;
import com.lucky.kali.userInfo.vo.req.GroupVO;

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

    /**
     * 查询组别分页信息
     *
     * @param pageCurrent 查询页数
     * @param pageSize    每页数量
     * @return 查询结果
     */
    CommonPage<GroupVO> selectGroupPageList(int pageCurrent, int pageSize);
}
