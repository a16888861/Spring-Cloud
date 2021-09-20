package com.lucky.kali.userinfo.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.lucky.kali.common.base.BaseController;
import com.lucky.kali.common.base.CommonPage;
import com.lucky.kali.common.dto.UserDTO;
import com.lucky.kali.common.exception.ExceptionUtil;
import com.lucky.kali.common.response.Response;
import com.lucky.kali.common.response.ResponseEnum;
import com.lucky.kali.common.response.ResponseInfo;
import com.lucky.kali.common.util.BeanUtil;
import com.lucky.kali.userinfo.service.UserService;
import com.lucky.kali.userinfo.vo.req.UserVO;
import com.lucky.kali.userinfo.vo.req.UserVOPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @author Elliot
 * @date 2021-06-02 22:18
 */
@Slf4j
@RestController("userController")
@RequestMapping("/userInfo/user")
@Api(value = "用户信息", tags = "用户信息接口")
@ApiSupport(order = 101, author = "Elliot")
public class UserController extends BaseController {

    @Resource
    private UserService userService;

    /**
     * 创建用户
     *
     * @param userVO 用户信息
     * @return 创建结果
     */
    @PostMapping("createUser")
    @ApiOperation(value = "创建用户", produces = "application/json",
            notes = "创建用户账号接口<br>" +
                    "组别暂定为superAdmin，admin，user<br>" +
                    "如果创建参数没有加roleId，则默认为普通用户", position = 2)
    @ApiOperationSupport(author = "Elliot")
    @SentinelResource(value = "createUser", blockHandlerClass = ExceptionUtil.class, blockHandler = "handleException")
    public ResponseInfo<Response> createUser(@Valid @RequestBody @ApiParam(name = "userVO", value = "用户信息实体", required = true) UserVO userVO,
                                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return Response.fail(bindingResult.getAllErrors().get(0).getDefaultMessage());
        }
        UserDTO userDTO = BeanUtil.copyProperties(userVO, UserDTO.class);
        return judgeResult(userService.createUser(userDTO));
    }

    /**
     * 查询用户分页信息
     *
     * @param userVoPage 查询条件
     * @return 查询结果
     */
    @PostMapping("selectUserPageList")
    @ApiOperation(value = "查询用户分页信息", produces = "application/json",
            notes = "查询用户分页信息接口<br>" +
                    "1.ID为：1430109634181881856 或 组别是超级管理员或者是管理员 默认是最高权限 可查询所有用户信息<br>" +
                    "2.如果不是用户组并且ID不为空，默认查询该ID创建的所有用户", position = 3)
    @ApiOperationSupport(author = "Elliot")
    public ResponseInfo<CommonPage<UserDTO>> selectUserPageList(@Valid @RequestBody UserVOPage userVoPage, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return Response.fail(bindingResult.getAllErrors().get(0).getDefaultMessage());
        }
        CommonPage<UserDTO> userPageList = userService.selectUserPageList(userVoPage);
        if (userPageList == null) {
            return Response.notFound(ResponseEnum.NOTFOUND.getMessage());
        }
        if (userPageList.getPageSize() <= 0) {
            return Response.notFound("user.userPageList.isEmpty");
        }
        return Response.success(ResponseEnum.SUCCESS.getMessage(), userPageList);
    }
}
