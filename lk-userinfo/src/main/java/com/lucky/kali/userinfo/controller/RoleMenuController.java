package com.lucky.kali.userinfo.controller;


import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.lucky.kali.common.aspect.Log;
import com.lucky.kali.common.base.BaseController;
import com.lucky.kali.common.context.UserContextUtil;
import com.lucky.kali.common.response.Response;
import com.lucky.kali.common.response.ResponseInfo;
import com.lucky.kali.userinfo.dto.RoleMenuDTO;
import com.lucky.kali.userinfo.service.RoleMenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 系统-角色菜单表 前端控制器
 *
 * @author Elliot
 * @since 2021-09-20
 */
@RestController
@RequestMapping("/userinfo/roleMenu")
@ApiSupport(order = 106, author = "Elliot")
@Api(value = "角色菜单信息", tags = "角色菜单信息接口")
public class RoleMenuController extends BaseController {

    @Resource
    private RoleMenuService roleMenuService;

    /**
     * 创建角色菜单(单个分配)
     *
     * @param roleId 角色id
     * @param menuId 菜单id
     * @return 创建结果
     */
    @Log("创建角色菜单(单个分配)")
    @PostMapping("createRoleMenu")
    @ApiOperation(value = "创建角色菜单(单个分配)", produces = "application/json",
            notes = "创建角色菜单(单个分配)<br>" +
                    "仅用于关联一级菜单")
    public ResponseInfo<Response> createRoleMenu(@ApiParam(value = "角色id", required = true) String roleId,
                                                 @ApiParam(value = "菜单id", required = true) String menuId) {
        RoleMenuDTO roleMenuDTO = RoleMenuDTO.builder()
                .roleId(roleId).menuId(menuId).createBy(UserContextUtil.getUserInfo().getId()).build();
        return judgeResult(roleMenuService.insert(roleMenuDTO));
    }
}
