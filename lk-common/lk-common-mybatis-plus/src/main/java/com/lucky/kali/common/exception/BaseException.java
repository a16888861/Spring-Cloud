package com.lucky.kali.common.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author Elliot
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class BaseException extends RuntimeException {

	private static final long serialVersionUID = 7130889471267813009L;

	private int status = 500;

	public BaseException(String message) {
		super(message);
	}

	public BaseException(String message, int status) {
		super(message);
		this.status = status;
	}

	public BaseException(Throwable cause) {
		super(cause);
	}

	public BaseException(String message, Throwable cause) {
		super(message, cause);
	}

	public BaseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
