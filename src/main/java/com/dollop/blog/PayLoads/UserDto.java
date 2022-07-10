package com.dollop.blog.PayLoads;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;



public class UserDto {
    private int id;
  
	@NotEmpty(message = "name can not be null")
	@Size(min=4,message="Username must be min of 4 characters!!")
	private String name;
    
	
	@Email(message = "Email is not valid!!")
	@NotEmpty(message = "Email can not be null!!")
	private String email;
    
	@NotEmpty(message = "must have a password")
	@Size(min=3,max=10,message="Password must be min of 3 chars and max of 10 chars")
	private String password;
    
	@NotEmpty(message = "you have'nt written anything")
	private String about;
	
	public UserDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public UserDto(int id, String name, String email, String password, String about) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.about = about;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAbout() {
		return about;
	}
	public void setAbout(String about) {
		this.about = about;
	}
	@Override
	public String toString() {
		return "UserDto [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", about="
				+ about + "]";
	}
	
	
	

}
