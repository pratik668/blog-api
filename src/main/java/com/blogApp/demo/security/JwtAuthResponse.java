package com.blogApp.demo.security;

import com.blogApp.demo.Payloads.UserDTO;

import lombok.Data;

@Data
public class JwtAuthResponse {

	private String token;

	private UserDTO user;
}
