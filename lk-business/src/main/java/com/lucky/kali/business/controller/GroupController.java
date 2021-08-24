package com.lucky.kali.business.controller;


import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.lucky.kali.business.dto.GroupDTO;
import com.lucky.kali.business.service.GroupService;
import com.lucky.kali.business.vo.req.GroupVO;
import com.lucky.kali.common.response.Response;
import com.lucky.kali.common.response.ResponseEnum;
import com.lucky.kali.common.response.ResponseInfo;
import com.lucky.kali.common.util.BeanUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * 组别表 前端控制器
 *
 * @author Elliot
 * @since 2021-08-23
 */
@Controller
@Api(value = "组别信息", tags = "组别信息接口")
@ApiSupport(order = 102, author = "Elliot")
@RequestMapping("/business/group")
public class GroupController {

    @Resource
    private GroupService groupService;

    @PostMapping("createGroup")
    @ApiOperation(value = "创建组别信息", produces = "application/json", notes = "管理员创建组别信息用的接口")
    @ApiOperationSupport(author = "Elliot")
    public ResponseInfo<Response> createUser(@Valid @RequestBody @ApiParam(name = "groupVO", value = "组别信息实体", required = true) GroupVO groupVO,
                                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return Response.fail(bindingResult.getAllErrors().get(0).getDefaultMessage());
        }

        GroupDTO groupDTO = BeanUtil.copyProperties(groupVO, GroupDTO.class);
        int result = groupService.createGroup(groupDTO);
        if (result > 0) {
            return Response.success(ResponseEnum.SUCCESS.getMessage());
        } else {
            return Response.fail(ResponseEnum.FAILURE.getMessage());
        }
    }
}
