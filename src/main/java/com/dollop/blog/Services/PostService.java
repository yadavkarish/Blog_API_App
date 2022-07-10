package com.dollop.blog.Services;

import java.util.List;

import com.dollop.blog.Entities.Post;
import com.dollop.blog.PayLoads.PostDto;

public interface PostService {
	
	PostDto createPost(PostDto postDto,Integer userId,Integer categoryId);
	
	PostDto updatePost(PostDto postDto, Integer postId);
	
	void deletePost(Integer postId);
	
	List<PostDto> getAllPosts();
	
	PostDto getPostById(Integer postId);
	
	List<PostDto> getAllPostsByCategory(Integer categoryId);
	
	List<PostDto> getPostsByUser(Integer userId);
	
	List<PostDto> searchPosts(String keyword);
	
}
