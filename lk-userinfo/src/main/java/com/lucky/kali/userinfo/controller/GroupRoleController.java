package com.lucky.kali.userinfo.controller;


import cn.hutool.core.lang.Assert;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.lucky.kali.common.response.Response;
import com.lucky.kali.common.response.ResponseEnum;
import com.lucky.kali.common.response.ResponseInfo;
import com.lucky.kali.userinfo.service.GroupRoleService;
import com.lucky.kali.userinfo.vo.resp.GroupRoleVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 系统-组别角色表 前端控制器
 *
 * @author Elliot
 * @since 2021-08-29
 */
@RestController("groupRoleController")
@Api(value = "组别角色信息", tags = "组别角色信息接口")
@ApiSupport(order = 104, author = "Elliot")
@RequestMapping("/userInfo/groupRole")
public class GroupRoleController {
    @Resource
    private GroupRoleService groupRoleService;

    /**
     * 查询组别角色对应关系List
     *
     * @param groupId 组别ID
     * @return 查询结果
     */
    @GetMapping("selectGroupRoleList/{groupId}")
    @ApiOperation(value = "查询组别角色对应关系List", produces = "application/json", notes = "查询组别角色对应关系List接口")
    @ApiOperationSupport(author = "Elliot")
    public ResponseInfo<GroupRoleVO> selectGroupRoleList(@ApiParam(value = "组别ID", required = true) @PathVariable String groupId) {
        try {
            Assert.notNull(groupId, "group.groupId.NotEmpty");
        } catch (IllegalArgumentException e) {
            return Response.fail(e.getMessage());
        }
        GroupRoleVO groupRoleVoList = groupRoleService.selectGroupRoleList(groupId);
        if (groupRoleVoList == null) {
            return Response.notFound("common.response.notfound");
        }
        if (groupRoleVoList.getRoleList().size() <= 0) {
            return Response.notFound("groupRole.groupRoleVoList.isEmpty");
        }
        return Response.success(ResponseEnum.SUCCESS.getMessage(), groupRoleVoList);
    }
}
