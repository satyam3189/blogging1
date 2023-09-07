package com.code.blogapp.services;

import java.util.List;
import java.util.stream.Collectors;

import com.code.blogapp.exception.ResourceNotFoundException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.code.blogapp.entities.User;
import com.code.blogapp.payloads.UserDto;
import com.code.blogapp.repositories.UserRepo;
import com.code.blogapp.services.UserService;
@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public UserDto createUser(UserDto userDto) {
		// TODO Auto-generated method stub
		User savedUser=this.userRepo.save(dtoToUser(userDto));
		return this.userToDto(savedUser);
	}
	
	private User dtoToUser(UserDto userDto) {
		User user= this.modelMapper.map(userDto, User.class);
		
		// below code work is done by model Mapper
//		user.setId(userDto.getId());
//		user.setName(userDto.getName());
//		user.setEmail(userDto.getEmail());
//		user.setAbout(userDto.getAbout());
//		user.setPassword(userDto.getName());
		return user;		
	}
	
	private UserDto userToDto(User user) {
		UserDto userdto=this.modelMapper.map(user,UserDto.class);
		
		// below code work is done by model Mapper

//		userdto.setId(user.getId());
//		userdto.setName(user.getName());
//		userdto.setEmail(user.getEmail());
//		userdto.setAbout(user.getAbout());
//		userdto.setPassword(user.getName());
		return userdto;		
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer id) {
		User user=this.userRepo.findById(id).orElseThrow((()-> new ResourceNotFoundException("User","id",id)));
		user.setAbout(userDto.getAbout());
		user.setEmail(userDto.getEmail());
		user.setName(userDto.getName());
		user.setPassword(userDto.getPassword());
		User updatedUser=this.userRepo.save(user);
		return this.userToDto(updatedUser);
	}

	@Override
	public UserDto getUserById(Integer id) {
		User user=userRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("User", "id", id));
		return this.userToDto(user);
	}

	@Override
	public List<UserDto> getallUsers() {
		List<User> users=userRepo.findAll();
		List<UserDto> userDtos=users.stream()
				.map(user->this.userToDto(user))
				.collect(Collectors.toList());
		return userDtos;
	}

	@Override
	public void deleteUser(Integer userId) {

		User user=this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","id", userId));
		this.userRepo.delete(user);
		
	}

}
