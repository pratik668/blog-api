package com.blogApp.demo.security;

import lombok.Data;

@Data
public class JwtAuthRequest {

	private String username;

	private String password;
}
