package com.lucky.kali.userinfo.dto;

import com.lucky.kali.common.base.BaseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

/**
 * 系统-菜单表
 *
 * @author Elliot
 * @since 2021-08-24
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(callSuper = true)
public class MenuDTO extends BaseDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 系统id
     */
    private String sysId;

    /**
     * 父级id
     */
    private String parentId;

    /**
     * 父级code
     */
    private String parentCode;

    /**
     * 上级ids
     */
    private String parentIds;

    /**
     * 菜单名称
     */
    private String name;

    /**
     * 菜单访问路径
     */
    private String href;

    /**
     * 菜单打开方式
     */
    private String target;

    /**
     * 图标
     */
    private String icon;

    /**
     * 排序
     */
    private String sort;

    /**
     * 菜单编码
     */
    private String code;

    /**
     * 菜单状态
     */
    private String status;

    /**
     * 是否显示
     */
    private String isShow;

    /**
     * 菜单类型(0总菜单，子菜单用1，2，3，4，5顺序向下区分)
     */
    private String type;


}
