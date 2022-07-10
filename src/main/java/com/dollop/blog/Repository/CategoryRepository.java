package com.dollop.blog.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dollop.blog.Entities.Category;

public interface CategoryRepository extends JpaRepository<Category,Integer> {

}
