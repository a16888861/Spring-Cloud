package com.lucky.kali.userinfo.controller;


import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.lucky.kali.common.aspect.Log;
import com.lucky.kali.common.base.BaseController;
import com.lucky.kali.common.context.UserContextUtil;
import com.lucky.kali.common.response.Response;
import com.lucky.kali.common.response.ResponseInfo;
import com.lucky.kali.userinfo.service.RoleMenuService;
import com.lucky.kali.userinfo.vo.req.RoleMenuVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

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
     * 创建角色菜单(支持单个和多个分配)
     *
     * @param roleMenuVO 角色ID和菜单ID实体
     * @param bindingResult 参数校验
     * @return 创建结果
     */
    @Log("创建角色菜单(单个分配)")
    @PostMapping("createRoleMenu")
    @ApiOperation(value = "创建角色菜单", produces = "application/json",
            notes = "创建角色菜单(支持单个和多个分配)<br>" +
                    "仅用于关联一级菜单")
    public ResponseInfo<Response> createRoleMenu(@Valid @RequestBody RoleMenuVO roleMenuVO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return Response.fail(bindingResult.getAllErrors().get(0).getDefaultMessage());
        }
        int result = 1;
        try {
            roleMenuVO.setUserId(UserContextUtil.getUserInfo().getId());
            roleMenuService.createRoleMenu(roleMenuVO);
            return judgeResult(result);
        } catch (Exception e) {
            e.printStackTrace();
            result = -1;
            return judgeResult(result);
        }
    }
}
