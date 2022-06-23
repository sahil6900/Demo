package com.microservices.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.microservices.entities.User;
import com.microservices.exceptions.ResourceNotFoundException;
import com.microservices.payload.UserDto;
import com.microservices.repository.UserRepo;
import com.microservices.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;
	
	@Override
	public UserDto createUser(UserDto userDto) {
		User user=this.dtoToUser(userDto);
		User savedUser = this.userRepo.save(user);
		
		return this.userToDto(savedUser) ;
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {
		User user = this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User", "Id", userId));
		
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail()); 
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());
		
		User updatedUser = this.userRepo.save(user);
		UserDto userToDto1 = this.userToDto(updatedUser);
		
		return userToDto1;
	}

	@Override
	public UserDto getUser(Integer userId) {
		User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","Id", userId));
		return this.userToDto(user);
	}

	@Override
	public List<UserDto> getAllUser() {
		List<User> listOfUsers = this.userRepo.findAll();
		
		List<UserDto> userDtos = listOfUsers.stream().map(user->this.userToDto(user)).collect(Collectors.toList());
		
		return userDtos;
		
	}

	@Override
	public void deleteUser(Integer userId) {
		User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "Id", userId));
		this.userRepo.delete(user);
	}
	
	private User dtoToUser(UserDto userDto) {
		User user = new User();
		
		user.setId(userDto.getId());
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());
		return user;
	}
	
	private UserDto userToDto(User user) {
		UserDto userDto = new UserDto();
		
		userDto.setId(user.getId());
		userDto.setName(user.getName());
		userDto.setEmail(user.getName());
		userDto.setPassword(user.getPassword());
		userDto.setAbout(user.getAbout());
		return userDto;
	}

}
