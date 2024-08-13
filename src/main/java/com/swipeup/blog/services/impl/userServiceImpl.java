package com.swipeup.blog.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.swipeup.blog.entity.User;
import com.swipeup.blog.exception.ResourceNotFoundException;
import com.swipeup.blog.payload.UserDto;
import com.swipeup.blog.repositories.UserRepo;
import com.swipeup.blog.services.UserService;

@Service
public class userServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;

	@Autowired

	private ModelMapper modelMapper;

	private Logger log = LoggerFactory.getLogger(userServiceImpl.class);

	@Override
	public UserDto createUser(UserDto userDto) {

		User user = this.userDtoToUser(userDto);

		User savedUser = userRepo.save(user);

		return this.userToUserDto(savedUser);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {
		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "UserId", userId));

		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setAbout(userDto.getAbout());
		user.setPassword(userDto.getPassword());

		User updatedUser = this.userRepo.save(user);
		return this.userToUserDto(updatedUser);
	}

	@Override
	public UserDto getUserById(Integer userId) {
		log.info("UserId " + userId);
		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "UserId", userId));

		return this.userToUserDto(user);
	}

	@Override
	public List<UserDto> getAllUsers() {
		List<User> allUsers = this.userRepo.findAll();
		List<UserDto> userDto = allUsers.stream().map(user -> this.userToUserDto(user)).collect(Collectors.toList());
		return userDto;
	}

	@Override
	public void deleteUserById(Integer id) {
		
		User user = this.userRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("User", "UserId", id));
		
	    this.userRepo.delete(user);
	    
	    

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
