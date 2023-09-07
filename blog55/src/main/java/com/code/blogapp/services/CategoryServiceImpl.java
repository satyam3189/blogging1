package com.code.blogapp.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.code.blogapp.entities.Category;
import com.code.blogapp.exception.ResourceNotFoundException;
import com.code.blogapp.payloads.CategoryDto;
import com.code.blogapp.repositories.CategoryRepo;
import com.code.blogapp.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CategoryDto createCategory(CategoryDto catDto) {
		Category cat =this.modelMapper.map(catDto, Category.class);
		Category savedCategory=this.categoryRepo.save(cat);
	return this.modelMapper.map(savedCategory, CategoryDto.class);
	}

	@Override
	public CategoryDto getCategory(Integer id) {
		Category cat =this.categoryRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("category", "id", id));
		
		return this.modelMapper.map(cat, CategoryDto.class);
	}

	@Override
	public List<CategoryDto> getCategories() {
List<Category> categories =this.categoryRepo.findAll();
		List<CategoryDto> catDtos=categories.stream()
				.map(cat->this.modelMapper.map(cat, CategoryDto.class))
				.collect(Collectors.toList());
		return catDtos;
	}

	@Override
	public CategoryDto updateCategory(CategoryDto catDto, Integer id) {
		Category cat =this.categoryRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("category", "id", id));
		cat.setAbout(catDto.getAbout());
		cat.setName(catDto.getName());
		Category updatedDat =this.categoryRepo.save(cat);
		return this.modelMapper.map(updatedDat, CategoryDto.class);
	}

	@Override
	public void deleteCategory(Integer id) {
		Category cat =this.categoryRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("category", "id", id));
		this.categoryRepo.delete(cat);
	}

}
