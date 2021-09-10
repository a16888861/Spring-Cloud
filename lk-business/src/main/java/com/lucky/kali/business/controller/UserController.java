package com.lucky.kali.business.controller;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONUtil;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.lucky.kali.common.dto.UserDTO;
import com.lucky.kali.business.service.UserService;
import com.lucky.kali.business.vo.req.UserVO;
import com.lucky.kali.business.vo.req.UserVOPage;
import com.lucky.kali.common.base.BaseEntity;
import com.lucky.kali.common.base.CommonPage;
import com.lucky.kali.common.response.Response;
import com.lucky.kali.common.response.ResponseEnum;
import com.lucky.kali.common.response.ResponseInfo;
import com.lucky.kali.common.util.BeanUtil;
import com.lucky.kali.common.util.JwtUtil;
import com.lucky.kali.common.util.Md5Utils;
import com.lucky.kali.common.vo.req.LoginVO;
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
@RequestMapping("/business/user")
@Api(value = "用户信息", tags = "用户信息接口")
@ApiSupport(order = 101, author = "Elliot")
public class UserController {

    @Resource
    private UserService userService;

    /**
     * 用户登陆
     *
     * @param loginVO       登陆信息
     * @param bindingResult 判断参数
     * @return 登陆结果
     */
    @PostMapping("doLogin")
    @ApiOperation(value = "登陆", produces = "application/json",
            notes = "登陆用的接口<br>" +
                    "组别id + 角色id + 用户名 + 密码 进行验证<br>" +
                    "利用Jwt生成token<br>" +
                    "\"groupId\": \"1430106533911797760\"<br>" +
                    "\"roleId\": \"1431876295237054464\"<br>" +
                    "\"mailOrPhone\": \"admin@mail.com\"<br>" +
                    "\"password\": \"TOBENO.1\"",
            position = 1)
    @ApiOperationSupport(author = "Elliot")
    public ResponseInfo<String> doLogin(@Valid @RequestBody LoginVO loginVO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return Response.fail(bindingResult.getAllErrors().get(0).getDefaultMessage());
        }

        loginVO.setPassword(Md5Utils.md5Hex(loginVO.getPassword()));
        UserDTO userDTO = userService.doLogin(loginVO);
        /*信息为空，则返回未找到*/
        if (ObjectUtil.isNull(userDTO)) {
            return Response.notFound("common.response.notfound");
        }
        /*如果用户状态是1，则代表用户被锁定，返回对应信息*/
        if (userDTO.getStatus().equals(BaseEntity.DEL_FLAG_DELETE)) {
            return Response.fail(ResponseEnum.USER_LOCK.getMessage());
        }
        /*密码不一致，返回错误信息*/
        if (!userDTO.getPassword().equals(loginVO.getPassword())) {
            return Response.fail("user.password.IsError");
        }
        String token = JwtUtil.createToken(JSONUtil.toJsonStr(userDTO));

        //TODO Token待存放到Redis中
//        UserDTO userDTO1 = JSONUtil.toBean(JwtUtil.getClaim(token), UserDTO.class);
//        log.info(userDTO1.toString());
//        log.info(token);
//        log.info(JwtUtil.getClaim(token));

        return Response.success("common.response.success", token, (String[]) null);
    }

    /**
     * 创建用户
     *
     * @param userVO 用户信息
     * @return 创建结果
     */
    //TODO Sentinel待配置
//    @SentinelResource(value = "createUser", blockHandler = "blockHandlerCommon")
    @PostMapping("createUser")
    @ApiOperation(value = "创建用户", produces = "application/json",
            notes = "创建用户账号接口<br>" +
                    "组别暂定为superAdmin，admin，user", position = 2)
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
    @ApiOperation(value = "查询用户分页信息", produces = "application/json",
            notes = "查询用户分页信息接口<br>" +
                    "1.ID为：1430109634181881856 或 组别是超级管理员或者是管理员 默认是最高权限 可查询所有用户信息<br>" +
                    "2.如果不是用户组并且ID不为空，默认查询该ID创建的所有用户", position = 3)
    @ApiOperationSupport(author = "Elliot")
    public ResponseInfo<CommonPage<UserDTO>> selectUserPageList(@RequestBody UserVOPage userVoPage, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return Response.fail(bindingResult.getAllErrors().get(0).getDefaultMessage());
        }
        CommonPage<UserDTO> userPageList = userService.selectUserPageList(userVoPage);
        if (userPageList == null) {
            return Response.notFound("common.response.notfound");
        }
        if (userPageList.getPageSize() <= 0) {
            return Response.notFound("user.userPageList.isEmpty");
        }
        return Response.success(ResponseEnum.SUCCESS.getMessage(), userPageList);
    }
}
