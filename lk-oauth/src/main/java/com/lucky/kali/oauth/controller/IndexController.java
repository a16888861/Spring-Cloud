package com.lucky.kali.oauth.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.lucky.kali.common.fegin.UserAccountClient;
import com.lucky.kali.common.response.Response;
import com.lucky.kali.common.response.ResponseEnum;
import com.lucky.kali.common.response.ResponseInfo;
import com.lucky.kali.common.vo.req.LoginVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Elliot
 * @date 2021-06-02 21:55
 */
@RestController
@Api(value = "首页信息", tags = "首页信息接口")
@ApiSupport(order = 100, author = "Elliot")
public class IndexController {
    @Resource
    private DiscoveryClient discoveryClient;

    @Resource
    private UserAccountClient userAccountClient;

    /**
     * buildName
     */
    @Value("${info.build.name}")
    private String buildName;
    /**
     * 获取Id
     */
    @Value("${spring.application.name}")
    private String applicationId;

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
                    "利用Jwt生成token", position = 1)
    @ApiOperationSupport(author = "Elliot")
    public ResponseInfo<String> login(@Valid @RequestBody LoginVO loginVO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return Response.fail(bindingResult.getAllErrors().get(0).getDefaultMessage());
        }
        return userAccountClient.doLogin(loginVO);
    }

    /**
     * Index信息
     *
     * @return Index信息
     */
    @GetMapping("/")
    @ApiOperation(value = "获取首页信息", produces = "application/json")
    @ApiOperationSupport(author = "Elliot")
    public ResponseInfo<List<String>> index() {
        List<String> result = new ArrayList<>();
        result.add("Welcome To The " + buildName + " ~");
        result.add("Priority of services : " + discoveryClient.getOrder());
        result.add("Service Id : " + applicationId);
        return Response.success(ResponseEnum.SUCCESS.getMessage(), result);
    }

    /**
     * 获取所有注册的服务名称
     *
     * @return 所有注册的服务
     */
    @GetMapping("getServices")
    @ApiOperation(value = "获取所有注册的服务名称", produces = "application/json")
    @ApiOperationSupport(author = "Elliot")
    public ResponseInfo<List<String>> getServices() {
        return Response.success("common.response.success", discoveryClient.getServices());
    }
}