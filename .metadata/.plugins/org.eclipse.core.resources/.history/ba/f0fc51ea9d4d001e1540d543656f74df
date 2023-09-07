package com.code.blogapp.payloads;

import java.util.ArrayList;
import java.util.List;

import javax.validation.*;
import javax.validation.constraints.*;

import com.code.blogapp.entities.Post;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {
	
	private Integer id;
	
	@NotEmpty(message="category name cann't be empty")
	private String name;
	
	@NotEmpty(message="category about cann't be empty")
	private String about;
	
}
