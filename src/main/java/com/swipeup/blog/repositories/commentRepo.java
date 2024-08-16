package com.swipeup.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.swipeup.blog.entity.Comment;

public interface commentRepo extends JpaRepository<Comment, Integer> {

}
