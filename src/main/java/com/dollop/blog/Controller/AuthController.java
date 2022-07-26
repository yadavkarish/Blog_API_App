package com.dollop.blog.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.dollop.blog.PayLoads.JwtAuthRequest;
import com.dollop.blog.PayLoads.JwtAuthResponse;
import com.dollop.blog.Security.JwtTokenHelper;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserDetailsService userDetailService;

	@Autowired
	public JwtTokenHelper jwtTokenHelper;

	@PostMapping("/login")
	public ResponseEntity<JwtAuthResponse> generateToken(@RequestBody JwtAuthRequest jwtAuthRequest) throws Exception {

		this.authenticate(jwtAuthRequest.getUsername(),jwtAuthRequest.getPassword());
		UserDetails userDetails=this.userDetailService.loadUserByUsername(jwtAuthRequest.getUsername());
		String token=this.jwtTokenHelper.generateToken(userDetails);
		return new ResponseEntity<JwtAuthResponse>(new JwtAuthResponse(token),HttpStatus.OK);
	}

	private void authenticate(String username, String password) throws BadCredentialsException  {
		UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(username, password);
		try {
		this.authenticationManager.authenticate(authenticationToken);
		}catch(BadCredentialsException ex) {
			System.out.println("Invalid User!!");
			throw new BadCredentialsException("Invalid username or password!!");
		}
	}

}
