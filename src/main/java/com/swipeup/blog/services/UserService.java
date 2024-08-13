package com.swipeup.blog.services;

import java.util.List;

import com.swipeup.blog.payload.UserDto;

public interface UserService  {
	
	
	public UserDto createUser(UserDto userDto);
	public UserDto updateUser(UserDto userDto,Integer userId);
	public UserDto getUserById(Integer id);
	public List<UserDto>   getAllUsers();
	public void  deleteUserById(Integer id);
	

}
