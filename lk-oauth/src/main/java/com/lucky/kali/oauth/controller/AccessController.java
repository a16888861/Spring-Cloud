package com.lucky.kali.oauth.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.lucky.kali.common.response.Response;
import com.lucky.kali.common.response.ResponseEnum;
import com.lucky.kali.common.response.ResponseInfo;
import com.lucky.kali.common.util.Md5Utils;
import com.lucky.kali.common.vo.req.LoginVO;
import com.lucky.kali.common.vo.resp.UserInfoVO;
import com.lucky.kali.oauth.fegin.UserFegin;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @author Elliot
 */
@RestController
@Api(value = "登陆信息", tags = "登陆信息接口")
@ApiSupport(order = 101, author = "Elliot")
@Slf4j
public class AccessController {

    @Resource
    private UserFegin userFegin;

    @PostMapping("doLogin")
    @ApiOperation(value = "用户登陆", produces = "application/json",
            notes = "登陆用的接口<br>" +
                    "组别id + 角色id + 用户名 + 密码 进行验证<br>" +
                    "生成token信息返回<br>" +
                    "groupId: 1430106533911797760<br>" +
                    "roleId: 1431876295237054464<br>" +
                    "mailOrPhone: admin@mail.com<br>" +
                    "password: TOBENO.1",
            position = 1)
    @ApiOperationSupport(author = "Elliot")
    public ResponseInfo<UserInfoVO> doLogin(@Valid LoginVO loginVO, BindingResult bindingResult) {
        log.info("登陆信息为：" + loginVO);
        if (bindingResult.hasErrors()) {
            return Response.fail(bindingResult.getAllErrors().get(0).getDefaultMessage());
        }
        loginVO.setPassword(Md5Utils.md5Hex(loginVO.getPassword()));
        /*调用Fegin接口查询登陆信息*/
        ResponseInfo<UserInfoVO> userInfo = userFegin.doLogin(loginVO);
        /*打印用户信息*/
        log.info("登陆成功，用户信息为：" + userInfo);
        /*成功*/
        if (userInfo.getStatusCode() == ResponseEnum.SUCCESS.getCode()) {
            Response.success(ResponseEnum.SUCCESS.getMessage(), userInfo);
        }
        /*失败*/
        if (userInfo.getStatusCode() == ResponseEnum.FAILURE.getCode()) {
            Response.fail(ResponseEnum.FAILURE.getMessage());
        }
        /*未找到*/
        if (userInfo.getStatusCode() == ResponseEnum.NOTFOUND.getCode()) {
            return Response.notFound(ResponseEnum.NOTFOUND.getMessage());
        }
//        String token = JwtUtil.createToken(JSONUtil.toJsonStr(userDTO));

        //TODO Token待存放到Redis中
//        UserDTO userDTO1 = JSONUtil.toBean(JwtUtil.getClaim(token), UserDTO.class);
//        log.info(userDTO1.toString());
//        log.info(token);
//        log.info(JwtUtil.getClaim(token));
        return userInfo;
    }
}
