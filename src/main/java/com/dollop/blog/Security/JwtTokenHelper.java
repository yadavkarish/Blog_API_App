package com.dollop.blog.Security;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenHelper {
	
	public static final long JWT_TOKEN_VALIDITY= 5*60*60;
	
	
	   private String SECRET_KEY = "jwtTokenKey";

	   /**
	    * @Description:this method used to get userName from token 
	    * @param token
	    * @return String (userName)
	    */
	    public String getUsernameFromToken(String token) {
	        return getClaimFromToken(token, Claims::getSubject);
	    }

	    /**
	     * @Description: this method used to get ExpirationDate of Token 
	     * @param token
	     * @return Date Object
	     */
	    public Date getExpirationDateFromToken(String token) {
	        return getClaimFromToken(token, Claims::getExpiration);
	    }

	    /**
	     * @Description: this method used to get ExpirationDate of Token 
	     * @param token
	     * @return Date Object
	     */
	    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
	        final Claims claims = getAllClaimsFromToken(token);
	        return claimsResolver.apply(claims);
	    }
	    private Claims getAllClaimsFromToken(String token) {
	        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
	    }

	    private Boolean isTokenExpired(String token) {
	        return getExpirationDateFromToken(token).before(new Date());
	    }

	    public String generateToken(UserDetails userDetails) {
	        Map<String, Object> claims = new HashMap<>();
	        return createToken(claims, userDetails.getUsername());
	    }

	    private String createToken(Map<String, Object> claims, String subject) {

	        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
	                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY*100))
	                .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
	    }

	    public Boolean validateToken(String token, UserDetails userDetails) {
	        final String username = getUsernameFromToken(token);
	        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	    }


}
