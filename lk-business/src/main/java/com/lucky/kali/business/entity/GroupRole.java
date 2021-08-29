package com.lucky.kali.business.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.lucky.kali.business.dto.GroupRoleDTO;
import com.lucky.kali.common.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

/**
 * 系统-组别角色表
 *
 * @author Elliot
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@TableName("kali_group_role")
public class GroupRole extends BaseEntity<GroupRole, GroupRoleDTO> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 组别id
     */
    private String groupId;

    /**
     * 角色id
     */
    private String roleId;

    @Override
    public GroupRoleDTO transDTO() {
        return GroupRoleDTO.builder().id(id)
                .groupId(groupId)
                .roleId(roleId)
                .createBy(createBy)
                .createDate(createDate)
                .updateBy(updateBy)
                .updateDate(updateDate)
                .delFlag(delFlag)
                .build();
    }

    @Override
    public GroupRole recDTO(GroupRoleDTO dto) {
        return GroupRole.builder().id(dto.getId())
                .groupId(dto.getGroupId())
                .roleId(dto.getRoleId())
                .createBy(dto.getCreateBy())
                .createDate(dto.getCreateDate())
                .updateBy(dto.getUpdateBy())
                .updateDate(dto.getUpdateDate())
                .delFlag(dto.getDelFlag())
                .build();
    }
}
