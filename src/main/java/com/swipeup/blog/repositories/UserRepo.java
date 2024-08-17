package com.swipeup.blog.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.swipeup.blog.entity.User;

public interface UserRepo extends JpaRepository<User,Integer > {

	
	Optional<User> findByEmail(String email);
}
