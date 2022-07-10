package com.dollop.blog.Controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

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
import com.dollop.blog.PayLoads.UserDto;
import com.dollop.blog.Services.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto)
	{
		return new ResponseEntity<>(this.userService.createUser(userDto),HttpStatus.CREATED);
		
	}
	
	@PutMapping("/{userId}")
	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto,@PathVariable("userId") Integer id)
	{
		return ResponseEntity.ok(this.userService.UpdateUser(userDto, id));	
	}
	
	
	@DeleteMapping("/{userId}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable("userId") Integer id)
	{
		this.userService.delete(id);
		return new ResponseEntity(new ApiResponse("User deleted successfully",true),HttpStatus.OK);
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity<UserDto> getUser(@PathVariable("userId") Integer id)
	{
		System.out.println(id);
		return ResponseEntity.ok(this.userService.getUserById(id));
	}
	
	@GetMapping("/")
	public ResponseEntity<List<UserDto>> getUsers()
	{
		return ResponseEntity.ok(this.userService.getAllUsers());
	}

	

}
