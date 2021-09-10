package com.lucky.kali.oauth.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 用户登录验证逻辑和权限处理
 *
 * @author Elliot
 */
@Service("userInfoServiceImpl")
@Slf4j
public class UserInfoServiceImpl implements UserDetailsService {

    @Resource
    private PasswordEncoder passwordEncoder;

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
