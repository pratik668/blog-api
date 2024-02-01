package com.blogApp.demo.Payloads;

import java.io.Serializable;

import com.blogApp.demo.utils.BaseModelMapper;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CatagoryDTO implements BaseModelMapper, Serializable {

	private static final long serialVersionUID = 1L;

	private int id;

	@NotBlank(message = "Category Title is required")
	private String categoryTitle;

	private String categoryDescription;

}
