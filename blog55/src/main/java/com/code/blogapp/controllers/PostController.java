package com.code.blogapp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.code.blogapp.payloads.ApiResponse;
import com.code.blogapp.payloads.PostDto;
import com.code.blogapp.payloads.PostResponse;
import com.code.blogapp.services.PostServiceImpl;


@RestController
@RequestMapping("/api")
public class PostController {
	
	@Autowired
	private PostServiceImpl postSerImpl;
	
	@PostMapping("/user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto post,
			@PathVariable Integer userId,@PathVariable Integer categoryId){
		PostDto savedPostDto=this.postSerImpl.createPost(post,userId,categoryId);
		return new ResponseEntity<PostDto>(savedPostDto,HttpStatus.CREATED);
	}
	
	@GetMapping("/posts/{id}")
	public ResponseEntity<PostDto> getPost(@PathVariable Integer id ){
		PostDto postDto=this.postSerImpl.getPost(id);
		return new ResponseEntity<PostDto>(postDto,HttpStatus.OK);
	}
	
	@GetMapping("/posts")
	public ResponseEntity<List<PostDto>> getAllPost(){
		List<PostDto> postDtos=this.postSerImpl.getAllPost();
		return new ResponseEntity<List<PostDto>>(postDtos,HttpStatus.OK);
	}
	@GetMapping("/searchTitle/{keyword}/posts")
	public ResponseEntity<List<PostDto>> getAllPostTitleSearch(@PathVariable String keyword){
		List<PostDto> postDtos=this.postSerImpl.searchPostsTitle(keyword);
		return new ResponseEntity<List<PostDto>>(postDtos,HttpStatus.OK);
	}
	
	@GetMapping("/paged/posts")
	public ResponseEntity<PostResponse> getAllPagingPost(
			@RequestParam(value="pageNo",required=false,defaultValue="0") Integer pageNo,
			@RequestParam(value="pageSize",required=false,defaultValue="4") Integer pageSize){
		PostResponse postDtos=this.postSerImpl.getAllPostPaging(pageNo,pageSize);
		return new ResponseEntity<PostResponse>(postDtos,HttpStatus.OK);
	}
	
	@GetMapping("/pagedSorted/posts")
	public ResponseEntity<PostResponse> getAllPagingSortedPost(
			@RequestParam(value="pageNo",required=false,defaultValue="0") Integer pageNo,
			@RequestParam(value="pageSize",required=false,defaultValue="4") Integer pageSize,
			@RequestParam(value="sortBy",required=false,defaultValue="id") String sortBy,
			@RequestParam(value="order",required=false,defaultValue="asc") String order){
		PostResponse postDtos=this.postSerImpl.getAllPostPagingSorting(pageNo,pageSize,sortBy,order);
		return new ResponseEntity<PostResponse>(postDtos,HttpStatus.OK);
	}
		
	@PutMapping("/posts/{id}")
	public ResponseEntity<PostDto> updatePost(@RequestBody PostDto post,
			@PathVariable Integer id){
		PostDto updatedPostDto=this.postSerImpl.updatePost(post, id);
		return new ResponseEntity<PostDto>(updatedPostDto,HttpStatus.OK);
	}
		
	@DeleteMapping("/posts/{id}")
	public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer id){
		this.postSerImpl.deletedPost(id);
		return new ResponseEntity<ApiResponse>(new ApiResponse("post deleted sucessfully",true),HttpStatus.OK);
	}
	
	@GetMapping("/user/{id}/posts")
	public ResponseEntity<List<PostDto>> getPostsByUser(@PathVariable Integer id){
		List<PostDto> posts=this.postSerImpl.getPostsByUser(id);
		return new ResponseEntity<List<PostDto>>(posts,HttpStatus.OK);
	}
	
	@GetMapping("/category/{id}/posts")
	public ResponseEntity<List<PostDto>> getPostsByCategory(@PathVariable Integer id){
		List<PostDto> posts=this.postSerImpl.getPostsByCategory(id);
		return new ResponseEntity<List<PostDto>>(posts,HttpStatus.OK);
	}
	
}
