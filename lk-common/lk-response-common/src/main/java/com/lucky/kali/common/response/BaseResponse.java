package com.lucky.kali.common.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * @author Elliot
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(callSuper = true)
public class BaseResponse extends Response {

	@ApiModelProperty(value = "状态码")
	private int statusCode = 200;
	@ApiModelProperty(value = "描述")
	private String message;

	public static BaseResponse error(String message) {
		return BaseResponse.builder().statusCode(500)
				.message(message).build();
	}

	public static BaseResponse ok(String message) {
		return BaseResponse.builder().statusCode(200)
				.message(message).build();
	}

	public static BaseResponse notFound(String message) {
		return BaseResponse.builder().statusCode(404)
				.message(message).build();
	}
}
