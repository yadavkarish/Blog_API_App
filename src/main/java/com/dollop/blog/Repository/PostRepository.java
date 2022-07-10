package com.dollop.blog.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dollop.blog.Entities.Category;
import com.dollop.blog.Entities.Post;
import com.dollop.blog.Entities.User;

public interface PostRepository extends JpaRepository<Post,Integer>{
	
	List<Post> findByUser(User user);
	List<Post> findByCategory(Category category);
	

}
