package com.code.blogapp.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.code.blogapp.entities.Category;
import com.code.blogapp.entities.Post;
import com.code.blogapp.entities.User;
import com.code.blogapp.exception.ResourceNotFoundException;
import com.code.blogapp.payloads.PostDto;
import com.code.blogapp.payloads.PostResponse;
import com.code.blogapp.repositories.CategoryRepo;
import com.code.blogapp.repositories.PostRepo;
import com.code.blogapp.repositories.UserRepo;
import com.code.blogapp.services.PostService;

@Service
public class PostServiceImpl implements PostService{

	@Autowired
	private PostRepo postRepo;
	@Autowired
	private ModelMapper modelMapper;
	
	
	@Autowired
	private CategoryRepo categoryRepo;
	@Autowired
	private UserRepo userRepo;
	
	
	
	@Override
	public PostDto createPost(PostDto postDto,Integer userId,Integer categoryId) {
		
		User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("user", "id", userId));
		Category category=this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("category", "id", categoryId));

		Post post=(this.modelMapper.map(postDto, Post.class));
		post.setImageName("default.png");
		post.setDateTimeOfPost(LocalDateTime.now());
		post.setCategory(category);
		post.setUser(user);
		Post newPost=this.postRepo.save(post);
		return this.modelMapper.map(newPost, PostDto.class);
	}

	@Override
	public PostDto getPost(Integer id) {
		Post savedPost=this.postRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Post", "id", id));
		return this.modelMapper.map(savedPost, PostDto.class);
	}

	@Override
	public List<PostDto> getAllPost() {
		List<Post> allPosts=this.postRepo.findAll();
		List<PostDto> allPostDtos=allPosts.stream()
								.map((post)->this.modelMapper.map(post, PostDto.class))
								.collect(Collectors.toList());
		return allPostDtos;
	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer id) {
		Post post=this.postRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Post", "id", id));
		post.setContent(postDto.getContent());
//		post.setDateTimeOfPost(postDto.getDateTimeOfPost());
		post.setImageName(postDto.getImageName());
		post.setTitle(postDto.getTitle());
//		post.setUser(postDto.getUser());
//		post.setCategory(postDto.getCategory());
		Post updatedPost=this.postRepo.save(post);
		return this.modelMapper.map(updatedPost, PostDto.class);
	}

	@Override
	public void deletedPost(Integer id) {
		Post post=this.postRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Post", "id", id));
		 this.postRepo.delete(post); 
 		
	}

	@Override
	public List<PostDto> getPostsByCategory(Integer catId) {
		Category category=this.categoryRepo.findById(catId).orElseThrow(()->new ResourceNotFoundException("category", "id", catId));
		List<Post> posts=this.postRepo.findByCategory(category);
		List<PostDto> postDtos=posts.stream().map(post->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return postDtos;
	}

	@Override
	public List<PostDto> getPostsByUser(Integer userId) {
		User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("user", "id",userId ));
		List<Post> posts=this.postRepo.findByUser(user);
		List<PostDto> postDtos=posts.stream().map(post->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return postDtos;
	}

	@Override
	public List<PostDto> searchPostsTitle(String keyword) {
		List<Post> posts=this.postRepo.findByTitleContaining(keyword);
		List<PostDto> postDtos=posts.stream()
				.map(post->this.modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());
		return postDtos;
	}

	@Override
	public PostResponse getAllPostPaging(Integer pageNo,Integer pageSize) {
		
		Pageable p=PageRequest.of(pageNo,pageSize);
		
		Page<Post> pagePosts=this.postRepo.findAll(p);
		List<Post> posts=pagePosts.getContent();
		
		List<PostDto> postDtos=posts.stream()
				.map(post -> this.modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());
		
		PostResponse postResponse=new PostResponse();
		postResponse.setContent(postDtos);
		postResponse.setLastPage(pagePosts.isLast());
		postResponse.setFirstPage(pagePosts.isFirst());
		postResponse.setPageNo(pagePosts.getNumber());
		postResponse.setPageSize(pagePosts.getSize());
		postResponse.setTotalPages(pagePosts.getTotalPages());
				return postResponse;
		
	}

	@Override
	public PostResponse getAllPostPagingSorting(Integer pageNo, Integer pageSize, String sortBy,String order) {
		Sort s=null;
if(order.equalsIgnoreCase("asc")) {
		s=Sort.by(sortBy);}   //for ascending
else {
		s=Sort.by(sortBy).descending();
		}
	Pageable p=PageRequest.of(pageNo,pageSize,s);		
		Page<Post> pagePosts=this.postRepo.findAll(p);
		List<Post> posts=pagePosts.getContent();
		
		List<PostDto> postDtos=posts.stream()
				.map(post -> this.modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());
		
		PostResponse postResponse=new PostResponse();
		postResponse.setContent(postDtos);
		postResponse.setLastPage(pagePosts.isLast());
		postResponse.setFirstPage(pagePosts.isFirst());
		postResponse.setPageNo(pagePosts.getNumber());
		postResponse.setPageSize(pagePosts.getSize());
		postResponse.setTotalPages(pagePosts.getTotalPages());
				return postResponse;
		
	}
	

}
