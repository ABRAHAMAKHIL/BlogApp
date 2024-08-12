package com.swipeup.blog.services.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.swipeup.blog.entity.User;
import com.swipeup.blog.payload.UserDto;
import com.swipeup.blog.repositories.UserRepo;
import com.swipeup.blog.services.UserService;

@Service
public class userServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;

	@Autowired

	private ModelMapper modelMapper;

	@Override
	public UserDto createUser(UserDto userDto) {

		User user = this.userDtoToUser(userDto);

		User savedUser = userRepo.save(user);

		return this.userToUserDto(savedUser);
	}

	@Override
	public UserDto updateUser(UserDto userDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserDto getUserById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserDto> getAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteUserById(Integer id) {
		// TODO Auto-generated method stub

	}

	public User userDtoToUser(UserDto userDto) {

		User user = this.modelMapper.map(userDto, User.class);

		return user;

	}

	public UserDto userToUserDto(User user) {

		UserDto userDto = this.modelMapper.map(user, UserDto.class);

		return userDto;
	}

}
