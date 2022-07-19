package com.dollop.blog.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dollop.blog.PayLoads.ApiResponse;
import com.dollop.blog.PayLoads.CommentDto;
import com.dollop.blog.Services.CommentService;

@RestController
@RequestMapping("/api/")
public class CommentController {
	
	@Autowired
	private CommentService commentService;

	@PostMapping("/post/{postId}/comments")
	public ResponseEntity<CommentDto> saveComment(@RequestBody CommentDto commentDto,@PathVariable("postId") Integer postId)
	{
		CommentDto commentDto1=this.commentService.createComment(commentDto, postId);
		return new ResponseEntity<CommentDto>(commentDto1,HttpStatus.CREATED);
	}
	
	
	@DeleteMapping("/comments/{commentId}")
	public ResponseEntity<ApiResponse> deleteComment(@PathVariable("commentId") Integer commentId)
	{
		this.commentService.deleteComment(commentId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("comment deleted successfully!!",true),HttpStatus.OK);
	}
	
}
