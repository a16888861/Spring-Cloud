package com.lucky.kali.userinfo.controller;


import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.lucky.kali.common.response.Response;
import com.lucky.kali.common.response.ResponseEnum;
import com.lucky.kali.common.response.ResponseInfo;
import com.lucky.kali.userinfo.vo.req.MenuVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 系统-菜单表 前端控制器
 *
 * @author Elliot
 * @since 2021-08-24
 */
@RestController("menuController")
@RequestMapping("/userInfo/menu")
@Api(value = "菜单信息", tags = "菜单信息接口")
@ApiSupport(order = 105, author = "Elliot")
public class MenuController {

    @PostMapping("createMenu")
    @ApiOperation(value = "创建菜单信息接口",produces = "application/json")
    public ResponseInfo<Response> createMenu(@Valid @RequestBody MenuVO menuVO, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return Response.fail(bindingResult.getAllErrors().get(0).getDefaultMessage());
        }
        return Response.success(ResponseEnum.SUCCESS.getMessage());
    }
}
