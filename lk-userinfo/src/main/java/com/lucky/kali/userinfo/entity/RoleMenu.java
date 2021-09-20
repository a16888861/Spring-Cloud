package com.lucky.kali.userinfo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.lucky.kali.common.base.BaseEntity;
import com.lucky.kali.userinfo.dto.RoleMenuDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

/**
 * 系统-角色菜单表
 *
 * @author Elliot
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@TableName("kali_role_menu")
public class RoleMenu extends BaseEntity<RoleMenu, RoleMenuDTO> {

    private static final long serialVersionUID = 1L;

    /**
     * 角色id
     */
    private String roleId;

    /**
     * 菜单id
     */
    private String menuId;

    /**
     * 元素id
     */
    private String elementId;

    @Override
    public RoleMenuDTO transDTO() {
        return RoleMenuDTO.builder().id(id)
                .roleId(roleId)
                .menuId(menuId)
                .elementId(elementId)
                .createBy(createBy)
                .createDate(createDate)
                .updateBy(updateBy)
                .updateDate(updateDate)
                .delFlag(delFlag)
                .build();
    }

    @Override
    public RoleMenu recDTO(RoleMenuDTO dto) {
        return RoleMenu.builder().id(dto.getId())
                .roleId(dto.getRoleId())
                .menuId(dto.getMenuId())
                .elementId(dto.getElementId())
                .createBy(dto.getCreateBy())
                .createDate(dto.getCreateDate())
                .updateBy(dto.getUpdateBy())
                .updateDate(dto.getUpdateDate())
                .delFlag(dto.getDelFlag())
                .build();
    }
}
