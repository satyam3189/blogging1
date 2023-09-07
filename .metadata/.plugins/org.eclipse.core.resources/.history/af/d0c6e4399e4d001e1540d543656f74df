package com.code.blogapp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.code.blogapp.entities.Category;
import com.code.blogapp.payloads.CategoryDto;
import com.code.blogapp.repositories.CategoryRepo;

public interface CategoryService {
	
	public CategoryDto createCategory(CategoryDto catDto);
	public CategoryDto getCategory(Integer id);
	public List<CategoryDto> getCategories();

	public CategoryDto updateCategory(CategoryDto catDto,Integer id);
	public void deleteCategory(Integer id);
	
}
