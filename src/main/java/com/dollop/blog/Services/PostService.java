package com.dollop.blog.Services;

import java.util.List;

import com.dollop.blog.Entities.Post;
import com.dollop.blog.PayLoads.PostDto;
import com.dollop.blog.PayLoads.PostResponse;

public interface PostService {
	
	PostDto createPost(PostDto postDto,Integer userId,Integer categoryId);
	
	PostDto updatePost(PostDto postDto, Integer postId);
	
	void deletePost(Integer postId);
	
	PostResponse getAllPosts(Integer pageNo,Integer pageSize,String sortBy,String order);
	
	PostDto getPostById(Integer postId);
	
	PostResponse getAllPostsByCategory(Integer categoryId, Integer pageNo, Integer pageSize,String sortBy,String order);
	
	PostResponse getPostsByUser(Integer userId,Integer pageNo,Integer pageSize,String sortBy,String order);
	
	List<PostDto> searchPosts(String keyword);
	
	
}
