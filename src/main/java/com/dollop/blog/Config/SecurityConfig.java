package com.dollop.blog.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.dollop.blog.Security.CustomUserDetailService;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
public class SecurityConfig  extends WebSecurityConfigurerAdapter{

	@Autowired
	private CustomUserDetailService customUserDetailService; 
	
    @Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		    .csrf()
		    .disable()
		    .authorizeHttpRequests()
		    .anyRequest()
		    .authenticated()
		    .and()
		    .httpBasic();
	}
    

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(this.customUserDetailService).passwordEncoder(passwordEncoder());
	}


	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
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
