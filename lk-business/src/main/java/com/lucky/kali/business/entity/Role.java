package com.lucky.kali.business.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.lucky.kali.business.dto.RoleDTO;
import com.lucky.kali.common.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

/**
 * 系统-角色表
 *
 * @author Elliot
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@TableName("kali_role")
public class Role extends BaseEntity<Role, RoleDTO> {

    private static final long serialVersionUID = 1L;

    /**
     * 系统id
     */
    private String sysId;

    /**
     * 角色代码
     */
    private String code;

    /**
     * 角色名称
     */
    private String name;

    /**
     * 角色英文名称
     */
    private String enName;

    /**
     * 角色类型
     */
    private String type;

    /**
     * 状态
     */
    private String status;

    /**
     * 数据权限
     */
    private String dataPermissionType;

    /**
     * 备注
     */
    private String remarks;


    @Override
    public RoleDTO transDTO() {
        return RoleDTO.builder().id(id)
                .sysId(sysId)
                .code(code)
                .name(name)
                .enName(enName)
                .type(type)
                .status(status)
                .dataPermissionType(dataPermissionType)
                .createBy(createBy)
                .createDate(createDate)
                .updateBy(updateBy)
                .updateDate(updateDate)
                .remarks(remarks)
                .delFlag(delFlag)
                .build();
    }

    @Override
    public Role recDTO(RoleDTO dto) {
        return Role.builder().id(dto.getId())
                .sysId(dto.getSysId())
                .code(dto.getCode())
                .name(dto.getName())
                .enName(dto.getEnName())
                .type(dto.getType())
                .status(dto.getStatus())
                .dataPermissionType(dto.getDataPermissionType())
                .createBy(dto.getCreateBy())
                .createDate(dto.getCreateDate())
                .updateBy(dto.getUpdateBy())
                .updateDate(dto.getUpdateDate())
                .remarks(dto.getRemarks())
                .delFlag(dto.getDelFlag())
                .build();
    }
}
