package com.dollop.blog.Security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter{
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private JwtTokenHelper jwtTokenHelper;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String requestTokenHeader = request.getHeader("Authorization");
		String userName = null;
		String jwtToken = null;

	// null and formate
		if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer")) {
			jwtToken = requestTokenHeader.substring(7);
			try {

				userName = this.jwtTokenHelper.getUsernameFromToken(jwtToken);
				System.out.println(userName);
			} catch (Exception e) {

				e.printStackTrace();
			}

			UserDetails userDetails = this.userDetailsService.loadUserByUsername(userName);
			// security

			if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
				
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
			
			   usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

			   SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			
			} else {
				System.out.println("username is null or context is not null");
			}
		}
		else
		{
			System.out.println("Jwt token does not begin with Bearer");
		}

		filterChain.doFilter(request, response);
	}

}
