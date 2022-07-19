package com.dollop.blog.Services.Implementation;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dollop.blog.Entities.Comment;
import com.dollop.blog.Entities.Post;
import com.dollop.blog.Exceptions.ResourceNotFoundException;
import com.dollop.blog.PayLoads.CommentDto;
import com.dollop.blog.PayLoads.PostDto;
import com.dollop.blog.Repository.PostRepository;
import com.dollop.blog.Repository.CommentRespository;
import com.dollop.blog.Services.CommentService;

@Service
public class CommentServiceImp implements CommentService {

	
	@Autowired
	private PostRepository postRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private CommentRespository commentRepo;
	
	
	@Override
	public CommentDto createComment(CommentDto commentDto, Integer postId) {
		Post post= this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","Id",postId));
		Comment comment=this.modelMapper.map(commentDto, Comment.class);
		comment.setPost(post);
		Comment saveComment=this.commentRepo.save(comment);
		return this.modelMapper.map(saveComment, CommentDto.class);
	}

	@Override
	public void deleteComment(Integer commentId) {
		Comment comment=this.commentRepo.findById(commentId).orElseThrow(()->new ResourceNotFoundException("Comment","commentId",commentId));
		this.commentRepo.delete(comment);
		
	}

}
