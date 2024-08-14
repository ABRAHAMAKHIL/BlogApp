package com.swipeup.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.swipeup.blog.entity.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer>{

}
