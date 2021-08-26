package com.lucky.kali.business.vo.req;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

/**
 * 角色表VO
 *
 * @author Elliot
 */
@Data
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
public class RoleVO implements Serializable {

    private static final long serialVersionUID = 1L;

}
