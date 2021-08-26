package com.lucky.kali.business.controller;


import cn.hutool.core.bean.BeanUtil;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.lucky.kali.business.dto.RoleDTO;
import com.lucky.kali.business.service.RoleService;
import com.lucky.kali.business.vo.req.RoleVO;
import com.lucky.kali.common.response.Response;
import com.lucky.kali.common.response.ResponseEnum;
import com.lucky.kali.common.response.ResponseInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * 系统-角色表 前端控制器
 *
 * @author Elliot
 * @since 2021-08-24
 */
@RestController("roleController")
@RequestMapping("/business/role")
@Api(value = "角色信息", tags = "角色信息接口")
@ApiSupport(order = 101, author = "Elliot")
public class RoleController {

    @Resource
    private RoleService roleService;

    /**
     * 创建角色信息
     *
     * @param roleVO        角色信息
     * @param bindingResult 参数判断
     * @return 创建结果
     */
    @PostMapping("createRole")
    @ApiOperation(value = "创建角色信息", produces = "application/json", notes = "创建角色信息用的接口")
    @ApiOperationSupport(author = "Elliot")
    public ResponseInfo<Response> createRole(@Valid @RequestBody @ApiParam(name = "roleVO", value = "角色信息实体", required = true) RoleVO roleVO,
                                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return Response.fail(bindingResult.getAllErrors().get(0).getDefaultMessage());
        }

        RoleDTO roleDTO = BeanUtil.copyProperties(roleVO, RoleDTO.class);
        int result = roleService.createRole(roleDTO);
        if (result > 0) {
            return Response.success(ResponseEnum.SUCCESS.getMessage());
        } else {
            return Response.fail(ResponseEnum.FAILURE.getMessage());
        }
    }
}
