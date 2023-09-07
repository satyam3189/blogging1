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
@Table(name="categories")

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Category {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@Column(length=45,nullable=false)
	private String name;
	
	@Column(nullable=false,length=250)
	private String about;
	
	@OneToMany(mappedBy="category",cascade=CascadeType.ALL)             //inverse side
	private List<Post> posts=new ArrayList<>();
	
	

}
