package com.code.blogapp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.code.blogapp.entities.Category;
import com.code.blogapp.entities.Post;
import com.code.blogapp.entities.User;




public interface PostRepo extends JpaRepository<Post, Integer> {

	List<Post> findByUser(User user);	
	List<Post> findByCategory(Category category);
	List<Post> findByTitleContaining(String keyword);
	
}
