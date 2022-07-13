package com.dollop.blog.Services.Implementation;

import java.util.Date;
import java.util.List;
import java.util.Locale.Category;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.dollop.blog.Entities.Post;
import com.dollop.blog.Entities.User;
import com.dollop.blog.Exceptions.ResourceNotFoundException;
import com.dollop.blog.PayLoads.PostDto;
import com.dollop.blog.PayLoads.PostResponse;
import com.dollop.blog.Repository.CategoryRepository;
import com.dollop.blog.Repository.PostRepository;
import com.dollop.blog.Repository.UserRepository;
import com.dollop.blog.Services.PostService;

@Service
public class PostServiceImp implements PostService{
	
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
		com.dollop.blog.Entities.Category category=this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category"," category id",categoryId));
		
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
	public PostResponse getAllPosts(Integer pageNo, Integer pageSize, String sortBy, String order) {
		Sort sort=order.equalsIgnoreCase("asc")?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
		Pageable page=PageRequest.of(pageNo, pageSize,sort);
		Page<Post> pagePosts=this.postRepo.findAll(page);
		List<Post> allPosts=pagePosts.getContent();
		List<PostDto> postDtos=allPosts.stream().map((post)->this.model.map(post, PostDto.class)).collect(Collectors.toList());
		return new PostResponse(postDtos,pagePosts.getNumber(),pagePosts.getSize(),pagePosts.getTotalElements(),pagePosts.getTotalPages(),pagePosts.isLast());
	}

	@Override
	public PostDto getPostById(Integer postId) {
		Post post=this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","post id",postId));
		return this.model.map(post, PostDto.class);
	}

	@Override
	public PostResponse getAllPostsByCategory(Integer categoryId, Integer pageNo, Integer pageSize, String sortBy,
			String order) {
		Sort sort=order.equalsIgnoreCase("asc")?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
		com.dollop.blog.Entities.Category category=this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","category id",categoryId));
		Pageable page=PageRequest.of(pageNo, pageSize,sort);
		Page<Post> pagePosts=this.postRepo.findByCategory(category,page);
		List<Post> allPosts=pagePosts.getContent();
		List<PostDto> collect =allPosts.stream().map((post)-> this.model.map(post,PostDto.class)).collect(Collectors.toList());
		return new PostResponse(collect,pagePosts.getNumber(),pagePosts.getSize(),pagePosts.getTotalElements(),pagePosts.getTotalPages(),pagePosts.isLast());
	}

	@Override
	public PostResponse getPostsByUser(Integer userId, Integer pageNo, Integer pageSize, String sortBy, String order) {
		Sort sort=order.equalsIgnoreCase("asc")?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
		User user=this.userRepo.findById( userId).orElseThrow(()->new ResourceNotFoundException("User","user id", userId));
		Pageable page=PageRequest.of(pageNo, pageSize,sort);
		Page<Post> pagePosts=this.postRepo.findByUser(user, page);
		List<Post> allPosts=pagePosts.getContent();
		List<PostDto> collect =allPosts.stream().map((post)-> this.model.map(post,PostDto.class)).collect(Collectors.toList());
		return new PostResponse(collect,pagePosts.getNumber(),pagePosts.getSize(),pagePosts.getTotalElements(),pagePosts.getTotalPages(),pagePosts.isLast());
		
	}

	@Override
	public List<PostDto> searchPosts(String keyword) {
		//List<Post> posts =this.postRepo.findByTitleContaining(keyword);
		List<Post> posts =this.postRepo.searchByTitle("%"+keyword+"%");
		List<PostDto> postDtos=posts.stream().map((post)->this.model.map(post,PostDto.class)).collect(Collectors.toList());
		return postDtos;
	}
	
	
	

}
