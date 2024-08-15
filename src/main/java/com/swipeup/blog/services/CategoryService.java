package com.swipeup.blog.services;

import java.util.List;

import com.swipeup.blog.payload.CategoryDto;

public interface CategoryService  {
	
	
	public CategoryDto createCategory(CategoryDto categorydto);
	public CategoryDto UpdateCategory(CategoryDto categorydto,Integer categoryId);
	public CategoryDto getCategoryByid(Integer categoryId);
	public void deleteCategoryByid(Integer categoryId);
	public List<CategoryDto> getAllCategories();
	

}
