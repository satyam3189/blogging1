package com.code.blogapp.payloads;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.validation.constraints.*;

import com.code.blogapp.entities.Category;
import com.code.blogapp.entities.User;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {
	
	private Integer id;

	@NotEmpty(message=" post's title cann't be empty")
	private String title;

	@NotEmpty(message=" post's content cann't be empty")
	private String content;


	private String imageName;
	
	private LocalDateTime dateTimeOfPost;
		
	private UserDto user;
	
	private CategoryDto category;

}
