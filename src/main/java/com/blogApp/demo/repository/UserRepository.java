package com.blogApp.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blogApp.demo.entities.User;
import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

	Optional<User> findByName(String name);

}
