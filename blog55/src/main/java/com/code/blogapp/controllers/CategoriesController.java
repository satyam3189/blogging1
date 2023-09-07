package com.code.blogapp.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.code.blogapp.payloads.ApiResponse;
import com.code.blogapp.payloads.CategoryDto;
import com.code.blogapp.services.CategoryServiceImpl;

@RestController
@RequestMapping("/api/categories")
public class CategoriesController {
	
	@Autowired
	private CategoryServiceImpl catServ;
	
	@GetMapping("/{id}")
	public ResponseEntity<CategoryDto> getCategory(@PathVariable Integer id){
		CategoryDto catDto=this.catServ.getCategory(id);
		return new ResponseEntity<>(catDto,HttpStatus.OK);
	}
	@GetMapping("/")
	public ResponseEntity<List<CategoryDto>> getCategories(){
		List<CategoryDto> catDtos=this.catServ.getCategories();
		return new ResponseEntity<>(catDtos,HttpStatus.OK);
	}
	@PostMapping("/")
	public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto catDto){
		
		CategoryDto savedCatDto=this.catServ.createCategory(catDto);
		return new ResponseEntity<>(savedCatDto,HttpStatus.CREATED);
	}
	@PutMapping("/{id}")
	public ResponseEntity<CategoryDto> UpdateCategory(@Valid @RequestBody CategoryDto catDto,Integer id){
		
		CategoryDto updatedCatDto=this.catServ.updateCategory(catDto,id);
		
		return new ResponseEntity<>(updatedCatDto,HttpStatus.OK);
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer id){
		this.catServ.deleteCategory(id);
		ApiResponse apiRes=new ApiResponse("category deleted sucessfully",true);
		return new ResponseEntity<>(apiRes,HttpStatus.OK);
	}
	
	

}
