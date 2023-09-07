package com.code.blogapp.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="users")
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Column(name="user_name",nullable=false,length=50)
	private String name;
	
	@Column(name="user_email",nullable=false,length=60)
	private String email;
	
	@Column(name="user_password",nullable=false,length=40)
	private String password;
	
	@Column(name="user_about",length=50)
	private String about;
	
	@OneToMany(mappedBy="user",cascade=CascadeType.ALL)  //inverse side
	private List<Post> posts=new ArrayList<>();   
	
}
