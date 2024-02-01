package com.blogApp.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blogApp.demo.entities.Role;

@Repository
public interface RoleRepo  extends JpaRepository<Role, Integer>{

}