package com.dollop.blog.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dollop.blog.Entities.Comment;

public interface CommentRespository extends JpaRepository<Comment,Integer> {

}
