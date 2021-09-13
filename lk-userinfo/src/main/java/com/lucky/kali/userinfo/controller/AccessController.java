package com.lucky.kali.userinfo.controller;

import cn.hutool.core.util.ObjectUtil;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.lucky.kali.common.base.BaseEntity;
import com.lucky.kali.common.constants.CommonConstants;
import com.lucky.kali.common.dto.UserDTO;
import com.lucky.kali.common.response.Response;
import com.lucky.kali.common.response.ResponseEnum;
import com.lucky.kali.common.response.ResponseInfo;
import com.lucky.kali.common.util.JwtUtil;
import com.lucky.kali.common.util.Md5Utils;
import com.lucky.kali.common.util.RedisUtil;
import com.lucky.kali.userinfo.service.UserService;
import com.lucky.kali.userinfo.vo.req.LoginVO;
import com.lucky.kali.common.vo.req.UserTokenVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.concurrent.TimeUnit;

/**
 * @author Elliot
 */
@RestController
@Api(value = "登陆信息", tags = "登陆信息接口")
@ApiSupport(order = 1, author = "Elliot")
@Slf4j
@RequestMapping("access")
public class AccessController {

    @Resource
    private UserService userService;
    @Resource
    private RedisUtil redisUtil;

    @PostMapping("doLogin")
    @ApiOperation(value = "用户登陆", produces = "application/json",
            notes = "登陆用的接口<br>" +
                    "用户名(邮箱或手机号) + 密码 进行验证<br>" +
                    "生成token信息返回<br>" +
                    "管理员：<br>" +
                    "mailOrPhone: admin@mail.com<br>" +
                    "password: TOBENO.1",
            position = 1)
    @ApiOperationSupport(author = "Elliot")
    public ResponseInfo<UserTokenVO> doLogin(@Valid @RequestBody LoginVO loginVO, BindingResult bindingResult) {
        log.info("登陆信息为：" + loginVO);
        /*信息校验*/
        if (bindingResult.hasErrors()) {
            return Response.fail(bindingResult.getAllErrors().get(0).getDefaultMessage());
        }
        /*密码加密*/
        loginVO.setPassword(Md5Utils.md5Hex(loginVO.getPassword()));
        UserDTO userDTO = userService.doLogin(loginVO);
        /*如果用户信息为空，返回用户未找到*/
        if (ObjectUtil.isNull(userDTO)) {
            return Response.notFound("user.isNull");
        }
        /*如果用户状态是1，则代表用户被锁定，返回对应信息*/
        if (userDTO.getStatus().equals(BaseEntity.DEL_FLAG_DELETE)) {
            return Response.custom(ResponseEnum.USER_LOCK.getCode(), ResponseEnum.USER_LOCK.getMessage(), null);
        }
        /*密码不一致，返回错误信息*/
        if (!userDTO.getPassword().equals(loginVO.getPassword())) {
            return Response.fail("user.password.IsError");
        }
        /*打印用户信息*/
        log.info("登陆成功，用户信息为：" + userDTO);
        /*token*/
        String token = JwtUtil.createToken(CommonConstants.USER_TOKEN + CommonConstants.HORIZONTAL_BAR + userDTO.getPhone() + CommonConstants.HORIZONTAL_BAR + userDTO.getMail());
        /*token过期时间(30分钟)*/
        long tokenExpiredTime = TimeUnit.SECONDS.toSeconds(1800);
        /*刷新token*/
        String refreshToken = JwtUtil.createToken(CommonConstants.USER_REFRESH_TOKEN + CommonConstants.HORIZONTAL_BAR + userDTO.getId());
        /*刷新token过期时间(2小时)*/
        long refreshTokenExpiredTime = TimeUnit.SECONDS.toSeconds(7200);
        UserTokenVO userTokenVO = UserTokenVO.builder()
                .token(token).tokenExpiredTime((int) tokenExpiredTime)
                .refreshToken(refreshToken).refreshTokenExpiredTime((int) refreshTokenExpiredTime)
                .build();
        /*Token相关信息存放到Redis中*/
        /*存储Token信息(30分钟后过期)*/
        redisUtil.set(CommonConstants.USER_TOKEN + CommonConstants.HORIZONTAL_BAR + userDTO.getPhone() + CommonConstants.HORIZONTAL_BAR + userDTO.getMail(),
                CommonConstants.TOKEN_PREFIX + CommonConstants.SPACE + token, tokenExpiredTime);
        /*存储刷新Token信息(2小时后过期)*/
        redisUtil.set(CommonConstants.USER_REFRESH_TOKEN + CommonConstants.HORIZONTAL_BAR + userDTO.getId(),
                CommonConstants.TOKEN_PREFIX + CommonConstants.SPACE + refreshToken, refreshTokenExpiredTime);
        return Response.success(ResponseEnum.SUCCESS.getMessage(), userTokenVO);
    }
}
