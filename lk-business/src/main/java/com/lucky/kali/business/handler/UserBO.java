package com.lucky.kali.business.handler;

import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.StringJoiner;

/**
 * 用户处理BO
 *
 * @author Elliot
 */
@Data
@SuperBuilder(toBuilder = true)
public class UserBO {
    /**
     * 用户来源
     */
    private String source;

    @Override
    public String toString() {
        return new StringJoiner(", ", UserBO.class.getSimpleName() + "[", "]")
                .add("source='" + source + "'")
                .toString();
    }
}
