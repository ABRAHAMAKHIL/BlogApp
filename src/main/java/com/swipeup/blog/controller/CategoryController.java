package com.swipeup.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.swipeup.blog.payload.ApiResponse;
import com.swipeup.blog.payload.CategoryDto;
import com.swipeup.blog.services.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@PostMapping("/create")
	public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categrydto) {

		CategoryDto savedCategory = this.categoryService.createCategory(categrydto);
		return new ResponseEntity<CategoryDto>(savedCategory, HttpStatus.CREATED);
	}

	@PutMapping("/update/{categoryId}")
	public ResponseEntity<CategoryDto> updateCategory( @Valid @RequestBody CategoryDto categoryDto,
			@PathVariable Integer categoryId) {

		CategoryDto updatedCategory = this.categoryService.UpdateCategory(categoryDto, categoryId);
		return new ResponseEntity<CategoryDto>(updatedCategory, HttpStatus.OK);
	}

	@DeleteMapping("/delete/{categoryId}")
	public ResponseEntity<ApiResponse> deleteCategoryById(@PathVariable Integer categoryId) {

		this.categoryService.deleteCategoryByid(categoryId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Category Deleted Successfully", true), HttpStatus.OK);
	}

	@GetMapping("/{categoryId}")
	public ResponseEntity<CategoryDto> getCategoryById(@PathVariable Integer categoryId) {

		CategoryDto category = this.categoryService.getCategoryByid(categoryId);
		return new ResponseEntity<CategoryDto>(category, HttpStatus.OK);
	}

	@GetMapping("/")
	public ResponseEntity<List<CategoryDto>> getAllCategories() {

		List<CategoryDto> category = this.categoryService.getAllCategories();
		return new ResponseEntity<List<CategoryDto>>(category, HttpStatus.OK);
	}

}
