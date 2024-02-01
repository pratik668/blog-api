package com.blogApp.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blogApp.demo.entities.Comment;

@Repository
public interface CommentRepo extends JpaRepository<Comment, Integer> {

}
