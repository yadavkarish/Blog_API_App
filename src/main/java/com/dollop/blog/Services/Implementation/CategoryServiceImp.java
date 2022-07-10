package com.dollop.blog.Services.Implementation;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dollop.blog.Entities.Category;
import com.dollop.blog.Exceptions.ResourceNotFoundException;
import com.dollop.blog.PayLoads.CategoryDto;
import com.dollop.blog.Repository.CategoryRepository;
import com.dollop.blog.Services.CategoryService;

@Service
public class CategoryServiceImp implements CategoryService{
	
	@Autowired
	private CategoryRepository categoryRepo;
	
	@Autowired
	private ModelMapper model;

	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		Category category=this.model.map(categoryDto,Category.class );
		return this.model.map(this.categoryRepo.save(category),CategoryDto.class);
	}

	@Override
	public CategoryDto UpdateCategory(CategoryDto categoryDto, Integer categoryId) {
		Category category=this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","Id",categoryId));
		category.setCategoryDescription(categoryDto.getCategoryDescription());
		category.setCategoryTitle(categoryDto.getCategoryTitle());
		return this.model.map(this.categoryRepo.save(category), CategoryDto.class);
	}

	@Override
	public CategoryDto getCategoryById(Integer categoryId) {
		Category category=this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","Id",categoryId));
		return this.model.map(category, CategoryDto.class) ;
	}

	@Override
	public List<CategoryDto> getAllCategories() {
		List<Category> categoriesList=this.categoryRepo.findAll();
		List<CategoryDto> categoryDtoList=categoriesList.stream().map((categoy)->
		  this.model.map(categoy, CategoryDto.class)).collect(Collectors.toList());
		return categoryDtoList;
	}

	@Override
	public void deleteCategory(Integer categoryId) {
		Category category=this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","Id",categoryId));
		this.categoryRepo.delete(category);
		
	}

	
}
