package com.lucky.kali.business.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.alibaba.csp.sentinel.util.StringUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lucky.kali.business.dto.GroupDTO;
import com.lucky.kali.business.dto.RoleDTO;
import com.lucky.kali.business.dto.UserDTO;
import com.lucky.kali.business.entity.User;
import com.lucky.kali.business.mapper.UserMapper;
import com.lucky.kali.business.service.GroupService;
import com.lucky.kali.business.service.RoleService;
import com.lucky.kali.business.service.UserService;
import com.lucky.kali.business.vo.req.LoginVO;
import com.lucky.kali.business.vo.req.UserVOPage;
import com.lucky.kali.common.base.BaseDTO;
import com.lucky.kali.common.base.BaseEntity;
import com.lucky.kali.common.base.BaseServiceImpl;
import com.lucky.kali.common.enums.GroupEnums;
import com.lucky.kali.common.util.CommonPage;
import com.lucky.kali.common.util.Md5Utils;
import com.lucky.kali.common.util.PageUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * 用户表 服务实现类
 *
 * @author Elliot
 */
@Slf4j
@Service("userService")
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl extends BaseServiceImpl<UserMapper, User, UserDTO> implements UserService {

    @Resource
    private GroupService groupService;
    @Resource
    private RoleService roleService;

    /**
     * 登陆接口
     *
     * @param loginVO 登陆信息
     * @return 查询结果
     */
    @Override
    public UserDTO doLogin(LoginVO loginVO) {
        GroupDTO groupDTO = groupService.selectById(loginVO.getGroupId());
        if (ObjectUtil.isNull(groupDTO)) {
            return null;
        }
        RoleDTO roleDTO = roleService.selectById(loginVO.getRoleId());
        if (ObjectUtil.isNull(roleDTO)) {
            return null;
        }
        UserDTO userDTO = selectOne(new LambdaQueryWrapper<User>()
                .eq(BaseEntity::getDelFlag, BaseEntity.DEL_FLAG_NORMAL)
                .eq(User::getUserGroup, groupDTO.getUserGroup())
                .eq(User::getRoleId, loginVO.getRoleId())
                .eq(User::getMail, loginVO.getMailOrPhone())
                .or()
                .eq(User::getPhone, loginVO.getMailOrPhone())
        );
        if (ObjectUtil.isNull(userDTO)) {
            return null;
        }
        return userDTO;
    }

    /**
     * 创建用户
     *
     * @param userDTO 用户信息
     * @return 创建结果
     */
    @Override
    public int createUser(UserDTO userDTO) {
        //TODO 创建者待添加 -> 完了写一个 上下文类 从服务上下文中获取创建者ID
        userDTO.setCreateBy("superAdmin");
        if (StringUtil.isBlank(userDTO.getPassword())) {
            /*如果没有填写密码，则设置初始密码为111111*/
            userDTO.setPassword(Md5Utils.md5Hex("111111"));
        } else {
            userDTO.setPassword(Md5Utils.md5Hex(userDTO.getPassword()));
        }
        //TODO 如果不存在角色ID 创建时 默认为普通用户
        userDTO.setUserGroup(GroupEnums.getGroupCode(userDTO.getUserGroup()));
        userDTO.setYear(String.valueOf(LocalDateTime.now().getYear()));
        userDTO.setStatus(BaseDTO.DEL_FLAG_NORMAL);
        int insert = insert(userDTO);
        if (insert > 0) {
            return insert;
        }
        return -1;
    }

    /**
     * 超级管理员查询用户分页信息
     *
     * @param userVoPage 查询条件
     * @return 查询结果
     */
    @Override
    public CommonPage<UserDTO> selectUserPageList(UserVOPage userVoPage) {
        Page<UserDTO> page = new Page<>();
        page.setCurrent(userVoPage.getPageCurrent()).setSize(userVoPage.getPageSize());
        Page<UserDTO> userPage;
        /*如果是超级管理员或者是管理员组别，默认查询所有*/
        //TODO 待添加 从上下文取当前用户信息存储的所有信息
//        if ("1430109634181881856".equals(userVoPage.getId()) || GroupEnums.GROUP_ADMIN.getGroupName().equals(userVoPage.getUserGroup())) {
        if ("1430109634181881856".equals(userVoPage.getId())) {
            userPage = selectPage(page, setSelectValue(new LambdaQueryWrapper<>(), userVoPage));
//        } else if (!GroupEnums.GROUP_USER.getGroupName().equals(userVoPage.getUserGroup()) && StringUtil.isNotBlank(userVoPage.getId())) {
        } else if (StringUtil.isNotBlank(userVoPage.getId())) {
            /*如果不是用户组并且id不为空，默认查询他自己创建的所有用户*/
            LambdaQueryWrapper<User> u = setSelectValue(new LambdaQueryWrapper<>(), userVoPage);
            u.eq(User::getCreateBy, userVoPage.getId());
            userPage = selectPage(page, u);
        } else {
            /*否则默认返回空*/
            return new CommonPage<>();
        }
        return PageUtil.transform(userPage, UserDTO.class);
    }

    /**
     * 公共页面查询条件赋值方法
     *
     * @param u          LambdaQueryWrapper<User>
     * @param userVoPage 赋值参数
     * @return 条件赋值结果
     */
    private LambdaQueryWrapper<User> setSelectValue(LambdaQueryWrapper<User> u, UserVOPage userVoPage) {
        u.eq(BaseEntity::getDelFlag, BaseEntity.DEL_FLAG_NORMAL)
                .eq(StringUtil.isNotBlank(userVoPage.getYear()), User::getYear, userVoPage.getYear())
                .like(StringUtil.isNotBlank(userVoPage.getName()), User::getName, userVoPage.getName())
                .like(StringUtil.isNotBlank(userVoPage.getMail()), User::getMail, userVoPage.getMail())
                .like(StringUtil.isNotBlank(userVoPage.getPhone()), User::getPhone, userVoPage.getPhone())
                .like(StringUtil.isNotBlank(userVoPage.getScreenName()), User::getScreenName, userVoPage.getScreenName())
                .like(StringUtil.isNotBlank(userVoPage.getUserGroup()), User::getUserGroup, GroupEnums.getGroupCode(userVoPage.getUserGroup()))
                .eq(StringUtil.isNotBlank(userVoPage.getStatus()), User::getStatus, userVoPage.getStatus());
        return u;
    }
}
