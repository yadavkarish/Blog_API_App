package com.dollop.blog.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dollop.blog.Entities.User;

public interface UserRepository extends JpaRepository<User,Integer> {

}
