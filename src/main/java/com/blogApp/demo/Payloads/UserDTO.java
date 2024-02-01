package com.blogApp.demo.Payloads;

import java.io.Serializable;

import org.hibernate.validator.constraints.Length;

import com.blogApp.demo.utils.BaseModelMapper;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDTO implements BaseModelMapper, Serializable {

	private static final long serialVersionUID = 1L;

	private int id;

	@NotBlank(message = "Name is required")
	private String name;

	@Email(message = "Enter valid email")
	private String email;

	@NotBlank(message = "password cannot blank")
	@Length(min = 5, max = 10, message = "Enter password must be minimum of 5 length and max of 10 length")
	private String password;

	private String about;

}
