package com.microservices.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.payload.ResponseApi;
import com.microservices.payload.UserDto;
import com.microservices.services.UserService;


@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	@PostMapping("/create")
	public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto){
		UserDto createUser = this.userService.createUser(userDto);
		return new ResponseEntity<UserDto>(createUser,HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto,@PathVariable Integer id){
		UserDto updateUser = this.userService.updateUser(userDto, id);
		return ResponseEntity.ok(updateUser);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseApi> deleteUser(@PathVariable Integer id){
		this.userService.deleteUser(id);
		return new ResponseEntity<ResponseApi>(new ResponseApi("User deleted successfully",true),HttpStatus.OK);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<UserDto>> getAllUsers(){
		List<UserDto> listOfUsers = this.userService.getAllUser();
		return new ResponseEntity<List<UserDto>>(listOfUsers,HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UserDto> getUser(@PathVariable Integer id){
		UserDto user = this.userService.getUser(id);
		return new ResponseEntity<UserDto>(user,HttpStatus.OK);
	}
}
