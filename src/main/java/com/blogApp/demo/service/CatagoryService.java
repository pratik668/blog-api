package com.blogApp.demo.service;

import java.util.List;

import com.blogApp.demo.Payloads.CatagoryDTO;

public interface CatagoryService {

	CatagoryDTO createCatagory(CatagoryDTO Catagory);

	CatagoryDTO getCatagoryById(Integer id);

	List<CatagoryDTO> getAllCatagories();

	void deleteCatagory(Integer CatagoryId);

	CatagoryDTO updateCatagory(CatagoryDTO CatagoryInput, Integer id);
}