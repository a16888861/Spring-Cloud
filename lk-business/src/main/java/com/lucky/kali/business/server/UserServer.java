package com.lucky.kali.business.server;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.lucky.kali.business.entity.Group;
import com.lucky.kali.business.service.GroupService;
import com.lucky.kali.business.service.RoleService;
import com.lucky.kali.business.service.UserService;
import com.lucky.kali.common.base.BaseEntity;
import com.lucky.kali.common.dto.GroupDTO;
import com.lucky.kali.common.dto.RoleDTO;
import com.lucky.kali.common.dto.UserDTO;
import com.lucky.kali.common.response.Response;
import com.lucky.kali.common.response.ResponseEnum;
import com.lucky.kali.common.response.ResponseInfo;
import com.lucky.kali.common.vo.req.LoginVO;
import com.lucky.kali.common.vo.resp.UserInfoVO;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @author Elliot
 */
@RestController
@RequestMapping("userServer")
public class UserServer {

    @Resource
    private UserService userService;
    @Resource
    private GroupService groupService;
    @Resource
    private RoleService roleService;

    /**
     * 用户登陆
     *
     * @param loginVO       登陆信息
     * @param bindingResult 判断参数
     * @return 登陆结果
     */
    @PostMapping("/doLogin")
    public ResponseInfo<UserInfoVO> doLogin(@Valid @RequestBody LoginVO loginVO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return Response.fail(bindingResult.getAllErrors().get(0).getDefaultMessage());
        }

        UserDTO userDTO = userService.doLogin(loginVO);
        /*信息为空，则返回未找到*/
        if (ObjectUtil.isNull(userDTO)) {
            return Response.notFound(ResponseEnum.NOTFOUND.getMessage());
        }
        /*如果用户状态是1，则代表用户被锁定，返回对应信息*/
        if (userDTO.getStatus().equals(BaseEntity.DEL_FLAG_DELETE)) {
            return Response.fail(ResponseEnum.USER_LOCK.getMessage());
        }
        /*密码不一致，返回错误信息*/
        if (!userDTO.getPassword().equals(loginVO.getPassword())) {
            return Response.fail("user.password.IsError");
        }
        /*查询组别信息*/
        GroupDTO groupDTO = groupService.selectOne(new LambdaQueryWrapper<Group>()
                .eq(BaseEntity::getDelFlag, BaseEntity.DEL_FLAG_NORMAL)
                .eq(Group::getUserGroup, userDTO.getUserGroup()));
        /*查询角色信息*/
        RoleDTO roleDTO = roleService.selectById(userDTO.getRoleId());
        UserInfoVO userInfoVO = UserInfoVO.builder().userDTO(userDTO).groupDTO(groupDTO).roleDTO(roleDTO).build();

        return Response.success(ResponseEnum.SUCCESS.getMessage(), userInfoVO);
    }
}
