package com.dollop.blog.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.dollop.blog.Security.CustomUserDetailService;
import com.dollop.blog.Security.JwtAuthenticationEntryPoint;
import com.dollop.blog.Security.JwtAuthenticationFilter;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class SecurityConfig  extends WebSecurityConfigurerAdapter{

	@Autowired
	private CustomUserDetailService customUserDetailService; 
	
	@Autowired
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
	
	@Autowired
	private JwtAuthenticationFilter jwtAuthenticationFilter;
	
    @Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		    .csrf()
		    .disable()
		    .authorizeHttpRequests()
		    .antMatchers("/api/v1/auth/login").permitAll()
		    .antMatchers(HttpMethod.GET).permitAll()
		    .anyRequest()
		    .authenticated()
		    .and()
		    .exceptionHandling()
		    .authenticationEntryPoint(this.jwtAuthenticationEntryPoint)
		    .and()
		    .sessionManagement()
		           .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		http.addFilterBefore(jwtAuthenticationFilter,UsernamePasswordAuthenticationFilter.class);
    }

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(this.customUserDetailService).passwordEncoder(passwordEncoder());
	}


	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		// TODO Auto-generated method stub
		return super.authenticationManagerBean();
	}
	
	
    
	/*
	 * @Bean SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	 * 
	 * http.csrf() .disable() .cors() .disable() .authorizeRequests()
	 * .antMatchers("/token").permitAll() .anyRequest().authenticated() .and()
	 * .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	 * 
	 * http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
	 * return http.build(); }
	 * 
	 * 
	 * @Bean AuthenticationManager authenticationManager() { return new
	 * AuthenticationManager() {
	 * 
	 * @Override public Authentication authenticate(Authentication authentication)
	 * throws AuthenticationException { // TODO Auto-generated method stub return
	 * null; } }; }
	 */
	

	
}
