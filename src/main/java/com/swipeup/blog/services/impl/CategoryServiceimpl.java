package com.swipeup.blog.services.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.swipeup.blog.entity.Category;
import com.swipeup.blog.exception.ResourceNotFoundException;
import com.swipeup.blog.payload.CategoryDto;
import com.swipeup.blog.repositories.CategoryRepo;
import com.swipeup.blog.services.CategoryService;

public class CategoryServiceimpl implements CategoryService {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private CategoryRepo categoryRepo;

	@Override
	public CategoryDto createCategory(CategoryDto categorydto) {
		Category savedCategory = this.categoryRepo.save(this.categoryDtoToCategory(categorydto));
		return this.categoryToCategoryDto(savedCategory);
	}

	@Override
	public CategoryDto UpdateCategory(CategoryDto categorydto, Integer categoryId) {
		Category category = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "CategoryId", categoryId));
		category.setDescription(categorydto.getDescription());
		category.setTitle(categorydto.getTitle());
		Category updatedCategory = this.categoryRepo.save(category);
		return this.categoryToCategoryDto(updatedCategory);
	}

	@Override
	public CategoryDto getCategoryByid(Integer categoryId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CategoryDto deleteCategoryByid(Integer categoryId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CategoryDto> getAllCategories() {
		// TODO Auto-generated method stub
		return null;
	}

	public Category categoryDtoToCategory(CategoryDto categorydto) {

		return modelMapper.map(categorydto, Category.class);

	}

	public CategoryDto categoryToCategoryDto(Category category) {

		return modelMapper.map(category, CategoryDto.class);

	}

}
