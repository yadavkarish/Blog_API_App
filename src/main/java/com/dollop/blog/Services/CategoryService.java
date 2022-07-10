package com.dollop.blog.Services;

import java.util.List;

import com.dollop.blog.PayLoads.CategoryDto;


public interface CategoryService {
   
	CategoryDto createCategory( CategoryDto categoryDto);
	CategoryDto UpdateCategory(CategoryDto categoryDto,Integer categoryId);
	CategoryDto getCategoryById(Integer categoryId);
	 List<CategoryDto> getAllCategories();
	 void deleteCategory(Integer categoryId);
	
}
