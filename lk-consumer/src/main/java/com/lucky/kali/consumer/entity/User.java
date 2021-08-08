package com.lucky.kali.consumer.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.lucky.kali.common.base.BaseEntity;
import com.lucky.kali.consumer.dto.UserDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
/**
 * 用户表Entity
 *
 * @author Elliot
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@TableName("kali_user")
public class User extends BaseEntity<User, UserDTO> {

    private static final long serialVersionUID = 1L;

    /**
     * 用户名
     */
    private String name;

    /**
     * 密码
     */
    private String password;

    /**
     * 邮箱
     */
    private String mail;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 用户名-别名
     */
    private String screenName;

    /**
     * 用户组别
     */
    private String userGroup;

    /**
     * 年份
     */
    private String year;



    @Override
    public UserDTO transDTO(){

    return UserDTO.builder().id(id)
        .name(name)
        .password(password)
        .mail(mail)
        .phone(phone)
        .screenName(screenName)
        .userGroup(userGroup)
        .year(year)
        .createBy(createBy)
        .createDate(createDate)
        .updateBy(updateBy)
        .updateDate(updateDate)
        .delFlag(delFlag)
    .build();
    }
    @Override
    public User recDTO(UserDTO dto) {

    return User.builder().id(dto.getId())
        .name(dto.getName())
        .password(dto.getPassword())
        .mail(dto.getMail())
        .phone(dto.getPhone())
        .screenName(dto.getScreenName())
        .userGroup(dto.getUserGroup())
        .year(dto.getYear())
        .createBy(dto.getCreateBy())
        .createDate(dto.getCreateDate())
        .updateBy(dto.getUpdateBy())
        .updateDate(dto.getUpdateDate())
        .delFlag(dto.getDelFlag())
    .build();
    }
}
