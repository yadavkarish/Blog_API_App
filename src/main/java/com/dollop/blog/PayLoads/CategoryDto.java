package com.dollop.blog.PayLoads;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class CategoryDto {

	private Integer categoriesId;
	
	@NotBlank
	@Size(min=4)
	private String categoryTitle;
	
	@NotBlank
	@Size(min=10)
	private String categoryDescription;
	
	
	
	public CategoryDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CategoryDto(Integer categoriesId, String categoryTitle, String categoryDescription) {
		super();
		this.categoriesId = categoriesId;
		this.categoryTitle = categoryTitle;
		this.categoryDescription = categoryDescription;
	}
	
	public Integer getCategoriesId() {
		return categoriesId;
	}
	public void setCategoriesId(Integer categoriesId) {
		this.categoriesId = categoriesId;
	}
	public String getCategoryTitle() {
		return categoryTitle;
	}
	public void setCategoryTitle(String categoryTitle) {
		this.categoryTitle = categoryTitle;
	}
	public String getCategoryDescription() {
		return categoryDescription;
	}
	public void setCategoryDescription(String categoryDescription) {
		this.categoryDescription = categoryDescription;
	}

	@Override
	public String toString() {
		return "CategoryDto [categoriesId=" + categoriesId + ", categoryTitle=" + categoryTitle
				+ ", categoryDescription=" + categoryDescription + "]";
	}
	
	
	
	
}
