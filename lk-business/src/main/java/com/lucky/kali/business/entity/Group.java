package com.lucky.kali.business.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.lucky.kali.common.base.BaseEntity;
import com.lucky.kali.common.dto.GroupDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

/**
 * 组别表
 *
 * @author Elliot
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@TableName("kali_group")
public class Group extends BaseEntity<Group, GroupDTO> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户组别
     */
    private String userGroup;

    /**
     * 用户组别名称
     */
    private String userGroupName;

    /**
     * 权限表id
     */
    private String permissionId;

    /**
     * 年份
     */
    private String year;


    @Override
    public GroupDTO transDTO() {
        return GroupDTO.builder().id(id)
                .userGroup(userGroup)
                .userGroupName(userGroupName)
                .permissionId(permissionId)
                .year(year)
                .createBy(createBy)
                .createDate(createDate)
                .updateBy(updateBy)
                .updateDate(updateDate)
                .delFlag(delFlag)
                .build();
    }

    @Override
    public Group recDTO(GroupDTO dto) {
        return Group.builder().id(dto.getId())
                .userGroup(dto.getUserGroup())
                .userGroupName(dto.getUserGroupName())
                .permissionId(dto.getPermissionId())
                .year(dto.getYear())
                .createBy(dto.getCreateBy())
                .createDate(dto.getCreateDate())
                .updateBy(dto.getUpdateBy())
                .updateDate(dto.getUpdateDate())
                .delFlag(dto.getDelFlag())
                .build();
    }
}
