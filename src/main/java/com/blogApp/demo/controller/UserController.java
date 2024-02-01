package com.blogApp.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blogApp.demo.Constants.AppConstants;
import com.blogApp.demo.Payloads.ApiResponse;
import com.blogApp.demo.Payloads.UserDTO;
import com.blogApp.demo.service.UserService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/")
	public ResponseEntity<ApiResponse<UserDTO>> addUser(@Valid @RequestBody UserDTO dto) {

		UserDTO user = userService.createUser(dto);
		ApiResponse<UserDTO> response = new ApiResponse();
		response.setData(user);
		response.setMsg(AppConstants.SUCCESS);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@PutMapping("/")
	public ResponseEntity<ApiResponse<UserDTO>> updateUser(@Valid @RequestBody UserDTO dto) {

		UserDTO user = userService.updateUser(dto, dto.getId());
		ApiResponse<UserDTO> response = new ApiResponse<>();
		response.setData(user);
		response.setMsg(AppConstants.SUCCESS);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> updateUser(@PathVariable("id") Integer id) {

		userService.deleteUser(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@GetMapping("/")
	public ResponseEntity<ApiResponse<List<UserDTO>>> getAllUser() {

		List<UserDTO> user = userService.getAllUsers();
		ApiResponse<List<UserDTO>> response = new ApiResponse<>();
		response.setData(user);
		response.setMsg(AppConstants.SUCCESS);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/{userId}")
	public ResponseEntity<ApiResponse<UserDTO>> getUser(@PathVariable("userId") Integer id) {

		UserDTO user = userService.getUserById(id);
		ApiResponse<UserDTO> response = new ApiResponse<>();
		response.setData(user);
		response.setMsg(AppConstants.SUCCESS);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
