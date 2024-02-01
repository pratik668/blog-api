package com.blogApp.demo.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blogApp.demo.Payloads.CatagoryDTO;
import com.blogApp.demo.entities.Category;
import com.blogApp.demo.exception.ResourceNotFoundException;
import com.blogApp.demo.repository.CatagoryRepository;
import com.blogApp.demo.service.CatagoryService;

@Service
public class CatagoryServiceImpl implements CatagoryService {

	@Autowired
	private CatagoryRepository catagoryRepository;

	@Override
	public CatagoryDTO createCatagory(CatagoryDTO Catagory) {
		Category cat = new Category().convert(Catagory);
		Category addedCat = catagoryRepository.save(cat);
		return new CatagoryDTO().convert(addedCat);
	}

	@Override
	public CatagoryDTO getCatagoryById(Integer id) {
		Category cat = this.catagoryRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "category id", id));

		return new CatagoryDTO().convert(cat);
	}

	@Override
	public List<CatagoryDTO> getAllCatagories() {
		List<Category> categories = this.catagoryRepository.findAll();
		List<CatagoryDTO> catDtos = categories.stream().map((cat) -> (CatagoryDTO) new CatagoryDTO().convert(cat))
				.collect(Collectors.toList());

		return catDtos;
	}

	@Override
	public void deleteCatagory(Integer catagoryId) {
		Category cat = this.catagoryRepository.findById(catagoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category ", "category id", catagoryId));
		this.catagoryRepository.delete(cat);
	}

	@Override
	public CatagoryDTO updateCatagory(CatagoryDTO categoryDto, Integer id) {
		Category cat = this.catagoryRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Category ", "Category Id", id));

		cat.setCategoryTitle(categoryDto.getCategoryTitle());
		cat.setCategoryDescription(categoryDto.getCategoryDescription());

		Category updatedcat = this.catagoryRepository.save(cat);

		return new CatagoryDTO().convert(updatedcat);
	}

}
