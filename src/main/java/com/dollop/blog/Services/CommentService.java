package com.dollop.blog.Services;

import com.dollop.blog.PayLoads.CommentDto;

public interface CommentService {
	
	CommentDto createComment(CommentDto commentDto, Integer postId);
	void deleteComment(Integer commentId);

}
