package com.dollop.blog.Controller;

import java.util.List;

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
import com.dollop.blog.PayLoads.PostDto;
import com.dollop.blog.Services.PostService;

@RestController
@RequestMapping("/api/")
public class PostController {
	
	
	 @Autowired
	  private PostService postService ;
	 
	
	@PostMapping("/user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto,@PathVariable("userId") Integer userId,@PathVariable("categoryId") Integer categoryId)
	{
		PostDto createpost=this.postService.createPost(postDto, userId, categoryId);
		return new ResponseEntity<PostDto>(createpost,HttpStatus.CREATED);
		
	}
	
	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<List<PostDto>> getPostsByUser(@PathVariable("userId") Integer userId){
		
		List<PostDto> posts=this.postService.getPostsByUser(userId);
		
		return new ResponseEntity<List<PostDto>> (posts,HttpStatus.OK);
		
	}
	
	@GetMapping("/category/{categoryId}/posts")
	public ResponseEntity<List<PostDto>> getPostsByCategory(@PathVariable("categoryId") Integer categotyId){
		
		List<PostDto> posts=this.postService.getAllPostsByCategory(categotyId);
		
		return new ResponseEntity<List<PostDto>> (posts,HttpStatus.OK);
		
	}
	
	@GetMapping("/posts")
	public ResponseEntity<List<PostDto>> getAllPosts(){
		
		List<PostDto> posts=this.postService.getAllPosts();
		
		return new ResponseEntity<List<PostDto>> (posts,HttpStatus.OK);
		
	}
	
	@GetMapping("/posts/{postId}")
	public ResponseEntity<PostDto> getPostById(@PathVariable("postId") Integer postId){
		
		PostDto postDto=this.postService.getPostById(postId);
		
		return new ResponseEntity<PostDto>(postDto,HttpStatus.OK);
		
	}
	
	@DeleteMapping("/posts/{postId}")
    public ApiResponse deletePostById(@PathVariable("postId") Integer postId){
		
	     this.postService.deletePost(postId);
		
		return new ApiResponse("Post is succssfully deleted!!",true);
		
	}
	
	@PutMapping("/posts/{postId}")
    public ResponseEntity<PostDto> updatePostById(@RequestBody PostDto postDto,@PathVariable("postId") Integer postId){
		
		PostDto updatePost=this.postService.updatePost(postDto, postId);
		return new ResponseEntity<PostDto>(updatePost,HttpStatus.OK);
		
	}
	
	
	

}
