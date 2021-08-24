package com.lucky.kali.business.vo.req;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

/**
 * 组别表VO
 *
 * @author Elliot
 */
@Data
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
public class GroupVO implements Serializable {
    private static final long serialVersionUID = 1L;

}
