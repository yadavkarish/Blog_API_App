package com.dollop.blog.Security;

import java.util.Optional;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.dollop.blog.Exceptions.ResourceNotFoundException;
import com.dollop.blog.Repository.UserRepository;


@Service
public class CustomUserDetailService implements UserDetailsService{

	@Autowired
	private UserRepository userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	   com.dollop.blog.Entities.User user=this.userRepo.findByEmail(username).orElseThrow(()->new ResourceNotFoundException("User","Email"+username,0));
		return user;
	}

}
