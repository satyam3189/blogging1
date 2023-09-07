package com.code.blogapp.controllers;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.code.blogapp.payloads.ApiResponse;
import com.code.blogapp.payloads.UserDto;
import com.code.blogapp.services.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired 
	private UserService userService;
	 
	@PostMapping("/")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
		UserDto createdUserDto=this.userService.createUser(userDto);
		return new ResponseEntity<>(createdUserDto,HttpStatus.CREATED);
	}
	@PutMapping("/{id}")
	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto,@PathVariable Integer id)
	{
		UserDto updatedUser=this.userService.updateUser(userDto, id);
		return new ResponseEntity<>(updatedUser,HttpStatus.OK);
		
		}
	//using map in response for message
//	@DeleteMapping("/{id}")
//	public ResponseEntity<?> deleteUser(@PathParam("id") Integer id){
//		this.userService.deleteUser(id);
//		return new ResponseEntity<>(Map.of("message","user deleted successfully"), HttpStatus.OK);
//	}
	
	
	//using apiReaponse (new)class for message

	@DeleteMapping("/{id}")
	public ApiResponse deleteUser(@PathVariable Integer id){
		this.userService.deleteUser(id);
		return new ApiResponse("user deleted succesfully",true);
	}
//	
	@GetMapping("/{id}")
	public ResponseEntity<UserDto> getUserById(@PathVariable Integer id){
		UserDto userById = this.userService.getUserById(id);
		return new ResponseEntity<>(userById,HttpStatus.OK);		
	}
	
	@GetMapping("/")
	public ResponseEntity<List<UserDto> > getAllUser(){
		List<UserDto> users = this.userService.getallUsers();
		return new ResponseEntity<>(users,HttpStatus.OK);		
	}

}
