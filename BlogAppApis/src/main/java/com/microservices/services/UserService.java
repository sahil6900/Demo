package com.microservices.services;

import java.util.List;

import com.microservices.payload.UserDto;

public interface UserService {

	UserDto createUser(UserDto user);
	
	UserDto updateUser(UserDto user,Integer userId);
	
	UserDto getUser(Integer userId);
	
	List<UserDto> getAllUser();
	
	void deleteUser(Integer userId);
}
