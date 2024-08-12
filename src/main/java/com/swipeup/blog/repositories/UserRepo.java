package com.swipeup.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.swipeup.blog.entity.User;

public interface UserRepo extends JpaRepository<User,Integer > {

}
