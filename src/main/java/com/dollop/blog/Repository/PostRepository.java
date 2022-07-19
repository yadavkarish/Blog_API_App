package com.dollop.blog.Repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dollop.blog.Entities.Category;
import com.dollop.blog.Entities.Post;
import com.dollop.blog.Entities.User;

public interface PostRepository extends JpaRepository<Post,Integer>{
	/*
	 * List<Post> findByUser(User user); List<Post> findByCategory(Category
	 * category);
	 */
	List<Post> findByTitleContaining(String title);
	Page<Post> findByUser(User user,Pageable page);
	Page<Post> findByCategory(Category category,Pageable page);
	
	@Query("select p from post p where p.title like :key") 
	List<Post> searchByTitle(@Param("key")String title);

}
