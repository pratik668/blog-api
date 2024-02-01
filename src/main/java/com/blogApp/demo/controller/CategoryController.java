package com.blogApp.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blogApp.demo.Payloads.CatagoryDTO;
import com.blogApp.demo.service.CatagoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

	@Autowired
	private CatagoryService categoryService;

	// create

	@PostMapping("/")
	public ResponseEntity<CatagoryDTO> createCategory(@Valid @RequestBody CatagoryDTO cateogDto) {
		CatagoryDTO createCategory = this.categoryService.createCatagory(cateogDto);
		return new ResponseEntity<CatagoryDTO>(createCategory, HttpStatus.CREATED);
	}

	// update

	@PutMapping("/{catId}")
	public ResponseEntity<CatagoryDTO> updateCategory(@Valid @RequestBody CatagoryDTO CatagoryDTO,
			@PathVariable Integer catId) {
		CatagoryDTO updatedCategory = this.categoryService.updateCatagory(CatagoryDTO, catId);
		return new ResponseEntity<CatagoryDTO>(updatedCategory, HttpStatus.OK);
	}

	// delete

	@DeleteMapping("/{catId}")
	public ResponseEntity<Void> deleteCategory(@PathVariable Integer catId) {
		this.categoryService.deleteCatagory(catId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	// get

	@GetMapping("/{catId}")
	public ResponseEntity<CatagoryDTO> getCategory(@PathVariable Integer catId) {

		CatagoryDTO CatagoryDTO = this.categoryService.getCatagoryById(catId);

		return new ResponseEntity<CatagoryDTO>(CatagoryDTO, HttpStatus.OK);

	}

	// get all
	@GetMapping("/")
	public ResponseEntity<List<CatagoryDTO>> getCategories() {
		List<CatagoryDTO> categories = this.categoryService.getAllCatagories();
		return ResponseEntity.ok(categories);
	}

}
