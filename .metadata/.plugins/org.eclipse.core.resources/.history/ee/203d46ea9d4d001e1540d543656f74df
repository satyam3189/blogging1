package com.code.blogapp.payloads;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.*;

import org.springframework.validation.annotation.Validated;

import com.code.blogapp.entities.Post;

import lombok.*;

@Data
public class UserDto {
	
private int id;
@NotEmpty(message="name cann't be empty")
@Size(min=4,message="name length must be greator than 3")
private String name;
@Email(message="email address not valid format")
private String email;
@Size(min=4,max=10,message="password length must be between 4 to 10")
private String password;
@NotEmpty(message="about cann't be empty")
private String about;


}
