package com.blogApp.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blogApp.demo.entities.Category;

@Repository
public interface CatagoryRepository extends JpaRepository<Category, Integer>{

}
