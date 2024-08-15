package com.swipeup.blog.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.swipeup.blog.entity.Category;
import com.swipeup.blog.exception.ResourceNotFoundException;
import com.swipeup.blog.payload.CategoryDto;
import com.swipeup.blog.repositories.CategoryRepo;
import com.swipeup.blog.services.CategoryService;


@Service
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

		Category category = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "CategoryId", categoryId));

		return this.categoryToCategoryDto(category);
	}

	@Override
	public void deleteCategoryByid(Integer categoryId) {
		Category category = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "CategoryId", categoryId));
		this.categoryRepo.delete(category);
	}

	@Override
	public List<CategoryDto> getAllCategories() {
		// TODO Auto-generated method stub
		List<Category> category = this.categoryRepo.findAll();
		List<CategoryDto> categoryDto = category.stream().map(ct -> this.categoryToCategoryDto(ct))
				.collect(Collectors.toList());
		return categoryDto;
	}

	public Category categoryDtoToCategory(CategoryDto categorydto) {

		return modelMapper.map(categorydto, Category.class);

	}

	public CategoryDto categoryToCategoryDto(Category category) {

		return modelMapper.map(category, CategoryDto.class);

	}

}
