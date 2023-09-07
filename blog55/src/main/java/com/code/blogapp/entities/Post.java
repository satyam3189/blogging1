package com.code.blogapp.entities;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.*;

import lombok.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="posts")
public class Post {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable=false,length=30)
	private String title;

	@Column(nullable=false,length=1000)
	private String content;

	@Column(name="image_name",nullable=false,length=30)
	private String imageName;
	
	@Column(name="posting_date_time",nullable=false)
	private LocalDateTime dateTimeOfPost;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;  //owning side
	
	@ManyToOne
	@JoinColumn(name="category_id")
	private Category category; //owning side
	
	
	

}
