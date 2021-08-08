package com.lucky.kali.common.base;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * BaseDTO类
 *
 * @author Elliot
 */
@Data
@ToString
@NoArgsConstructor
@Accessors(chain = true)
@SuperBuilder(toBuilder = true)
public class BaseDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 删除标记（0：正常；1：删除；2：审核）
     */
    public static final String DEL_FLAG_NORMAL = "0";
    public static final String DEL_FLAG_DELETE = "1";
    public static final String DEL_FLAG_AUDIT = "2";

    /**
     * 标识符
     */
    protected String id;

    /**
     * 创建者
     */
    protected String createBy;

    /**
     * 创建日期
     */
    protected LocalDateTime createDate;

    /**
     * 更新者
     */
    protected String updateBy;

    /**
     * 更新日期
     */
    protected LocalDateTime updateDate;

    /**
     * 删除标记
     */
    protected String delFlag;
}
