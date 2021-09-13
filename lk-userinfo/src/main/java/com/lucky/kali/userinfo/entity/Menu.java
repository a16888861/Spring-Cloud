package com.lucky.kali.userinfo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.lucky.kali.common.base.BaseEntity;
import com.lucky.kali.userinfo.dto.MenuDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

/**
 * 系统-菜单表
 *
 * @author Elliot
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@TableName("kali_menu")
public class Menu extends BaseEntity<Menu, MenuDTO> implements Serializable {

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
     * 菜单类型
     */
    private String type;


    @Override
    public MenuDTO transDTO() {
        return MenuDTO.builder().id(id)
                .sysId(sysId)
                .parentId(parentId)
                .parentCode(parentCode)
                .parentIds(parentIds)
                .name(name)
                .href(href)
                .target(target)
                .icon(icon)
                .sort(sort)
                .code(code)
                .status(status)
                .isShow(isShow)
                .type(type)
                .createBy(createBy)
                .createDate(createDate)
                .updateBy(updateBy)
                .updateDate(updateDate)
                .delFlag(delFlag)
                .build();
    }

    @Override
    public Menu recDTO(MenuDTO dto) {
        return Menu.builder().id(dto.getId())
                .sysId(dto.getSysId())
                .parentId(dto.getParentId())
                .parentCode(dto.getParentCode())
                .parentIds(dto.getParentIds())
                .name(dto.getName())
                .href(dto.getHref())
                .target(dto.getTarget())
                .icon(dto.getIcon())
                .sort(dto.getSort())
                .code(dto.getCode())
                .status(dto.getStatus())
                .isShow(dto.getIsShow())
                .type(dto.getType())
                .createBy(dto.getCreateBy())
                .createDate(dto.getCreateDate())
                .updateBy(dto.getUpdateBy())
                .updateDate(dto.getUpdateDate())
                .delFlag(dto.getDelFlag())
                .build();
    }
}
