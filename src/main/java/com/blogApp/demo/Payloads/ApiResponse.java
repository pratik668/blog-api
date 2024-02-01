package com.blogApp.demo.Payloads;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(content = Include.NON_NULL)
public class ApiResponse<T> {

	private String msg;
	private boolean success = true;
	private T data;

	public ApiResponse(String msg, boolean success, T data) {
		super();
		this.msg = msg;
		this.success = success;
		this.data = data;
	}

}
