package com.dollop.blog.Services.Implementation;


import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dollop.blog.Entities.Category;
import com.dollop.blog.Entities.Post;
import com.dollop.blog.Entities.User;
import com.dollop.blog.Exceptions.ResourceNotFoundException;
import com.dollop.blog.PayLoads.PostDto;
import com.dollop.blog.Repository.CategoryRepository;
import com.dollop.blog.Repository.PostRepository;
import com.dollop.blog.Repository.UserRepository;
import com.dollop.blog.Services.PostService;

@Service
public class PostServiceImp implements PostService {

	@Autowired
	private PostRepository postRepo;
	
	@Autowired
	private ModelMapper model;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private CategoryRepository categoryRepo;
	
	@Override
	public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {
		User user= this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","id",userId));
		Category category=this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category"," category id",categoryId));
		
		Post post=this.model.map(postDto, Post.class);
		post.setImageName("");
		post.setAddDate(new Date());
		post.setUser(user);
		post.setCategory(category);
		
		Post newPost=this.postRepo.save(post);
		return this.model.map(newPost, PostDto
				.class);
	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {
		Post post=this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","post id",postId));
		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		post.setImageName(postDto.getImageName());
		
		Post updatedPost=this.postRepo.save(post);
		return this.model.map(updatedPost,PostDto.class);
	}

	@Override
	public void deletePost(Integer postId) {
		Post post=this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","post id",postId));
		this.postRepo.delete(post);
		
	}

	@Override
	public List<PostDto> getAllPosts() {
		List<Post> allPosts=this.postRepo.findAll();
		List<PostDto> postDtos=allPosts.stream().map((post)->this.model.map(post, PostDto.class)).collect(Collectors.toList());
		return postDtos;
	}

	@Override
	public PostDto getPostById(Integer postId) {
		Post post=this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","post id",postId));
		return this.model.map(post, PostDto.class);
	}

	@Override
	public List<PostDto> getAllPostsByCategory(Integer categoryId) {
		Category category=this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","category id",categoryId));
		List<Post> posts=this.postRepo.findByCategory(category);
		List<PostDto> collect =posts.stream().map((post)-> this.model.map(post,PostDto.class)).collect(Collectors.toList());
		return collect;
	}

	@Override
	public List<PostDto> getPostsByUser(Integer userId) {
		User user=this.userRepo.findById( userId).orElseThrow(()->new ResourceNotFoundException("User","user id", userId));
		List<Post> posts=this.postRepo.findByUser(user);
		List<PostDto> collect =posts.stream().map((post)-> this.model.map(post,PostDto.class)).collect(Collectors.toList());
		return collect;
	}

	@Override
	public List<PostDto> searchPosts(String keyword) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
