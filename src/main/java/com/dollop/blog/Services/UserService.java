package com.dollop.blog.Services;


import java.util.List;

import com.dollop.blog.PayLoads.UserDto;

public interface UserService {
	
	 UserDto createUser(UserDto user);
	 UserDto UpdateUser(UserDto user,Integer userId);
	 UserDto getUserById(Integer userId);
	 List<UserDto> getAllUsers();
	 void delete(Integer userId);

}
