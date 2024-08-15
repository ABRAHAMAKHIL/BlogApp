package com.swipeup.blog.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.swipeup.blog.entity.Category;
import com.swipeup.blog.entity.Post;
import com.swipeup.blog.entity.User;

public interface PostRepo  extends JpaRepository<Post, Integer>{
	
	List<Post> findByUser(User user);
	List<Post> findByCategory(Category category);

}
