package com.dollop.blog.Services.Implementation;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dollop.blog.Entities.User;
import com.dollop.blog.Exceptions.ResourceNotFoundException;
import com.dollop.blog.PayLoads.UserDto;
import com.dollop.blog.Repository.UserRepository;
import com.dollop.blog.Services.UserService;

@Service
public class UserServiceImp implements UserService {
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public UserDto createUser(UserDto userDto) {
		
		User user=this.userRepo.save(this.dtoToUser(userDto));
		return this.usertoUserDto(user);
	}

	@Override
	public UserDto UpdateUser(UserDto userDto, Integer userId) {
		User user= this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","id",userId));
		  
		user.setName(userDto.getName());
		   user.setEmail(userDto.getEmail());
		   user.setPassword(userDto.getPassword());
		   user.setAbout(userDto.getAbout());
		return this.usertoUserDto(this.userRepo.save(user));
	}

	@Override
	public UserDto getUserById(Integer userId) {
		User user =this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","id",userId));
		return this.usertoUserDto(user);
	}

	@Override 
	public List<UserDto> getAllUsers() {
		List<User> usersList=this.userRepo.findAll();
		List<UserDto> userDtoList=usersList.stream().map(users->this.usertoUserDto(users)).collect(Collectors.toList()); 
		return userDtoList;
	}

	@Override
	public void delete(Integer userId) {
		User user =this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","id",userId));
		this.userRepo.delete(user);
	}
	
	private User dtoToUser(UserDto userDto)
	{
	   User user=this.modelMapper.map(userDto,User.class);
	    
		/*
		 * User user=new User(); user.setId(userDto.getId());
		 * user.setName(userDto.getName()); user.setEmail(userDto.getEmail());
		 * user.setPassword(userDto.getPassword()); user.setAbout(userDto.getAbout());
		 */
		 
	   return user;
	}
	
	private UserDto usertoUserDto(User user)
	{
		UserDto userDto=this.modelMapper.map(user, UserDto.class);
		 
		/*
		 * UserDto userDto=new UserDto(); userDto.setId(user.getId());
		 * userDto.setName(user.getName()); userDto.setEmail(user.getEmail());
		 * userDto.setPassword(user.getPassword()); userDto.setAbout(user.getAbout());
		 */
		return userDto;
	}

}
