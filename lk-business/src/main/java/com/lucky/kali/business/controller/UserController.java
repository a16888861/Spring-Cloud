package com.lucky.kali.business.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.lucky.kali.business.dto.UserDTO;
import com.lucky.kali.business.service.UserService;
import com.lucky.kali.business.vo.req.UserVOPage;
import com.lucky.kali.business.vo.req.UserVO;
import com.lucky.kali.common.response.Response;
import com.lucky.kali.common.response.ResponseEnum;
import com.lucky.kali.common.response.ResponseInfo;
import com.lucky.kali.common.util.BeanUtil;
import com.lucky.kali.common.util.CommonPage;
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
 * @author Elliot
 * @date 2021-06-02 22:18
 */
@RestController("userController")
@Api(value = "用户信息", tags = "用户信息接口")
@ApiSupport(order = 101, author = "Elliot")
@RequestMapping("/business/user")
public class UserController {

    @Resource
    private UserService userService;

    /**
     * 创建用户
     *
     * @param userVO 用户信息
     * @return 创建结果
     */
    //TODO Sentinel待配置
//    @SentinelResource(value = "createUser", blockHandler = "blockHandlerCommon")
    @PostMapping("createUser")
    @ApiOperation(value = "超级管理员创建用户", produces = "application/json", notes = "超级管理员创建用户")
    @ApiOperationSupport(author = "Elliot")
    public ResponseInfo<Response> createUser(@Valid @RequestBody @ApiParam(name = "userVO", value = "用户信息实体", required = true) UserVO userVO,
                                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return Response.fail(bindingResult.getAllErrors().get(0).getDefaultMessage());
        }

        UserDTO userDTO = BeanUtil.copyProperties(userVO, UserDTO.class);
        int user = userService.createUser(userDTO);
        if (user > 0) {
            return Response.success(ResponseEnum.SUCCESS.getMessage());
        } else {
            return Response.fail(ResponseEnum.FAILURE.getMessage());
        }
    }

    /**
     * 查询用户分页信息
     *
     * @param userVoPage 查询条件
     * @return 查询结果
     */
    @PostMapping("selectUserPageList")
    @ApiOperation(value = "超级管理员查询用户分页信息", produces = "application/json", notes = "超级管理员查询用户分页信息")
    @ApiOperationSupport(author = "Elliot")
    public ResponseInfo<CommonPage<UserDTO>> selectUserPageList(@RequestBody UserVOPage userVoPage) {
        CommonPage<UserDTO> userPageList = userService.selectUserPageList(userVoPage);
        if (userPageList.getPageSize() <= 0) {
            return Response.notFound("group.groupPageList.isEmpty");
        }
        return Response.success(ResponseEnum.SUCCESS.getMessage(), userPageList);
    }
}
