package com.dollop.blog.Controller;

import java.util.List;

import javax.validation.Valid;

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

import com.dollop.blog.PayLoads.ApiResponse;
import com.dollop.blog.PayLoads.CategoryDto;
import com.dollop.blog.PayLoads.UserDto;
import com.dollop.blog.Services.CategoryService;

@RestController
@RequestMapping("/api/categoty")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	@PostMapping("/")
	public ResponseEntity<CategoryDto> createCategory(@Valid  @RequestBody  CategoryDto categoryDto)
	{
		return new ResponseEntity<CategoryDto>(this.categoryService.createCategory(categoryDto),HttpStatus.CREATED);
	}
	
	@PutMapping("/{categoryId}")
	public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto,@PathVariable("categoryId") Integer id)
	{
		return ResponseEntity.ok(this.categoryService.UpdateCategory(categoryDto, id));	
	}
	
	@DeleteMapping("/{categoryId}")
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable("categoryId") Integer id)
	{
		this.categoryService.deleteCategory(id);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Category deleted successfully",true),HttpStatus.OK);
	}
	
	@GetMapping("/{categoryId}")
	public ResponseEntity<CategoryDto> getCategory(@PathVariable("categoryId") Integer id)
	{
		System.out.println(id);
		return ResponseEntity.ok(this.categoryService.getCategoryById(id));
	}
	
	@GetMapping("/")
	public ResponseEntity<List<CategoryDto>> getUsers()
	{
		return ResponseEntity.ok(this.categoryService.getAllCategories());
	}
}
