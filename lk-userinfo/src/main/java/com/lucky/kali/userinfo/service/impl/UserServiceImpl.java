package com.lucky.kali.userinfo.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.alibaba.csp.sentinel.util.StringUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lucky.kali.common.base.BaseDTO;
import com.lucky.kali.common.base.BaseEntity;
import com.lucky.kali.common.base.BaseServiceImpl;
import com.lucky.kali.common.base.CommonPage;
import com.lucky.kali.common.constants.CommonConstants;
import com.lucky.kali.common.context.UserContextUtil;
import com.lucky.kali.common.dto.UserDTO;
import com.lucky.kali.common.enums.GroupEnums;
import com.lucky.kali.common.util.Md5Utils;
import com.lucky.kali.common.util.PageUtil;
import com.lucky.kali.userinfo.entity.User;
import com.lucky.kali.userinfo.mapper.UserMapper;
import com.lucky.kali.userinfo.service.UserService;
import com.lucky.kali.userinfo.vo.req.LoginVO;
import com.lucky.kali.userinfo.vo.req.UserVOPage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    /**
     * 登陆接口
     *
     * @param loginVO 登陆信息
     * @return 查询结果
     */
    @Override
    public UserDTO doLogin(LoginVO loginVO) {
        UserDTO userDTO = selectOne(new LambdaQueryWrapper<User>()
                .eq(BaseEntity::getDelFlag, BaseEntity.DEL_FLAG_NORMAL)
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
        /*从服务上下文中获取创建者ID*/
        userDTO.setCreateBy(UserContextUtil.getUserInfo().getId());
        if (StringUtil.isBlank(userDTO.getPassword())) {
            /*如果没有填写密码，则设置初始密码为111111*/
            userDTO.setPassword(Md5Utils.md5Hex("111111"));
        } else {
            userDTO.setPassword(Md5Utils.md5Hex(userDTO.getPassword()));
        }
        //TODO 如果不存在角色ID 创建时 默认为普通用户
//        if (StringUtil.isBlank(userDTO.getRoleId())){
//            userDTO.setRoleId();
//        }
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
        boolean selectAll;
        /*如果当前登录的用户是超级管理员，并且组别为管理员或者超级管理员，默认查询所有*/
        selectAll = UserContextUtil.getUserInfo().getId().equals(userVoPage.getId()) &&
                GroupEnums.GROUP_ADMIN.getGroupName().equals(userVoPage.getUserGroup()) ||
                GroupEnums.GROUP_SUPER_ADMIN.getGroupName().equals(userVoPage.getUserGroup());
        if (selectAll) {
            userPage = selectPage(page, setSelectValue(new LambdaQueryWrapper<>(), userVoPage, CommonConstants.SELECT_ALL));
            return PageUtil.transform(userPage, UserDTO.class);
        }
        /*如果不是用户组并且id不为空，默认查询他自己创建的所有用户*/
        selectAll = !GroupEnums.GROUP_USER.getGroupName().equals(userVoPage.getUserGroup()) &&
                StringUtil.isNotBlank(userVoPage.getId());
        if (selectAll) {
            LambdaQueryWrapper<User> u = setSelectValue(new LambdaQueryWrapper<>(), userVoPage, "");
            u.eq(User::getCreateBy, userVoPage.getId());
            userPage = selectPage(page, u);
            return PageUtil.transform(userPage, UserDTO.class);
        } else {
            /*否则默认返回空*/
            return new CommonPage<>();
        }
    }

    /**
     * 公共页面查询条件赋值方法
     *
     * @param u          LambdaQueryWrapper<User>
     * @param userVoPage 赋值参数
     * @return 条件赋值结果
     */
    private LambdaQueryWrapper<User> setSelectValue(LambdaQueryWrapper<User> u, UserVOPage userVoPage, String isAll) {
        if (CommonConstants.SELECT_ALL.equals(isAll)) {
            userVoPage.setUserGroup(null);
        }
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
