package com.blogApp.demo.service;

import java.util.List;

import com.blogApp.demo.Payloads.UserDTO;

public interface UserService {

	UserDTO createUser(UserDTO user);
	
	UserDTO getUserById(Integer id);
	
	List<UserDTO> getAllUsers();
	
	void deleteUser(Integer userId);

	UserDTO updateUser(UserDTO userInput, Integer id);
	
	UserDTO registerNewUser(UserDTO user);
}
