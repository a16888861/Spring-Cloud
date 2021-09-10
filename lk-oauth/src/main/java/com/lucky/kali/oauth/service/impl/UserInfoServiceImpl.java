package com.lucky.kali.oauth.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.lucky.kali.common.base.BaseEntity;
import com.lucky.kali.common.dto.UserDTO;
import com.lucky.kali.common.response.Response;
import com.lucky.kali.common.response.ResponseEnum;
import com.lucky.kali.common.response.ResponseInfo;
import com.lucky.kali.common.util.Md5Utils;
import com.lucky.kali.common.vo.req.LoginVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * 用户登录验证逻辑和权限处理
 *
 * @author Elliot
 */
@Service("userInfoServiceImpl")
@Slf4j
public class UserInfoServiceImpl implements UserInfoService {

    @Resource
    private PasswordEncoder passwordEncoder;

    /**
     * 用户登录接口
     *
     * @param loginVO 登录信息
     * @return 登录结果
     */
    @Override
    public ResponseInfo<UserDTO> loadUserByUsername(@Valid LoginVO loginVO) {
//        if (bindingResult.hasErrors()) {
//            Response.fail(bindingResult.getAllErrors().get(0).getDefaultMessage());
//        }
        log.info("登录信息为：" + loginVO);
        loginVO.setPassword(Md5Utils.md5Hex(loginVO.getPassword()));
        UserDTO userDTO = null;
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
        return Response.success("common.response.success",userDTO);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

//        // 从数据库验证用户密码 查询用户权限
//        //  rabc
//        TbUser tbUser = tbUserService.getByUsername(username);
//        List<GrantedAuthority> grantedAuthorities = Lists.newArrayList();
//        if (tbUser != null) {
//            List<TbPermission> tbPermissions = tbPermissionMapper.selectByUserId(tbUser.getId());
//
//            tbPermissions.stream().forEach(tbPermission -> {
//                if (tbPermission != null && tbPermission.getEnname() != null) {
//                    GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(tbPermission.getEnname());
//                    grantedAuthorities.add(grantedAuthority);
//                }
//            });
//        }
//
//        return new User(tbUser.getUsername(), tbUser.getPassword(), grantedAuthorities);

        // 逻辑用户名随便输入 只要密码是123456 即可登录系统并拥有admin权限
        if (username != null) {
            User admin = new User(username, this.passwordEncoder.encode("123456"), AuthorityUtils.createAuthorityList("admin"));
            log.info("验证信息：" + username + this.passwordEncoder.encode("123456"));
            return admin;
        } else {
            throw new UsernameNotFoundException("用户[" + username + "]不存在");
        }
    }


}
