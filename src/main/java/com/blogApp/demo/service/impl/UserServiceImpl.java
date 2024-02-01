package com.blogApp.demo.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.blogApp.demo.Constants.AppConstants;
import com.blogApp.demo.Payloads.UserDTO;
import com.blogApp.demo.entities.Role;
import com.blogApp.demo.entities.User;
import com.blogApp.demo.exception.ResourceNotFoundException;
import com.blogApp.demo.repository.RoleRepo;
import com.blogApp.demo.repository.UserRepository;
import com.blogApp.demo.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private RoleRepo roleRepo;

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDTO createUser(UserDTO user) {
		User entity = new User().convert(user);
		User savedEntity = userRepository.save(entity);
		return new UserDTO().convert(savedEntity);
	}

	@Override
	public UserDTO updateUser(UserDTO userInput, Integer id) {
		// TODO Auto-generated method stub
		User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "User Id", id));

		user.setName(userInput.getName());
		user.setEmail(userInput.getEmail());
		user.setPassword(userInput.getPassword());
		user.setAbout(userInput.getAbout());
		User updatedUser = userRepository.save(user);
		return new UserDTO().convert(updatedUser);
	}

	@Override
	public UserDTO getUserById(Integer id) {
		// TODO Auto-generated method stub
		User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "User Id", id));

		return new UserDTO().convert(user);
	}

	@Override
	public List<UserDTO> getAllUsers() {

		List<User> users = userRepository.findAll();
		List<UserDTO> dto = users.stream().map(user -> (UserDTO) new UserDTO().convert(user))
				.collect(Collectors.toList());

		return dto;
	}

	@Override
	public void deleteUser(Integer userId) {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "User Id", userId));

		userRepository.deleteById(userId);

	}

	@Override
	public UserDTO registerNewUser(UserDTO userDto) {
		User user = this.modelMapper.map(userDto, User.class);

		// encoded the password
		user.setPassword(this.passwordEncoder.encode(user.getPassword()));

		// roles
		Role role = this.roleRepo.findById(AppConstants.NORMAL_USER).get();

		user.getRole().add(role);

		User newUser = this.userRepository.save(user);

		return this.modelMapper.map(newUser, UserDTO.class);
	}

}
