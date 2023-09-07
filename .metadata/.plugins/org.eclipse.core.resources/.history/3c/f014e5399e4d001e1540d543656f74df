package com.code.blogapp.services;

import java.util.List;

import com.code.blogapp.entities.Category;
import com.code.blogapp.entities.User;
import com.code.blogapp.payloads.PostDto;
import com.code.blogapp.payloads.PostResponse;

public interface PostService {
	
	public PostDto createPost(PostDto postDto,Integer userId,Integer categoryId);
	public PostDto getPost(Integer id);
	public List<PostDto> getAllPost();

	public PostResponse getAllPostPaging(Integer pageNo,Integer pageSize);
	public PostResponse getAllPostPagingSorting(Integer pageNo,Integer pageSize,String sortBy,String order);
	public PostDto updatePost(PostDto postDto,Integer id);
	public void deletedPost(Integer id);
	
	public List<PostDto> getPostsByCategory(Integer categoryId);
	public List<PostDto> getPostsByUser(Integer userId );
	
	public List<PostDto> searchPostsTitle(String keyword);
	
	

}
