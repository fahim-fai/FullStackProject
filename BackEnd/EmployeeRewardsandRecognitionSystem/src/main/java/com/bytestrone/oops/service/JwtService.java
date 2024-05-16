package com.bytestrone.oops.service;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
	
	//to check the authenticity of token
	private static final String SECRET_KEY= "0PuEN8xGBEB3p9owsf0AwRiPWFXRQSmJlYBwNMObgVW0ALEh2ARR8XAqvClIUxM2";
	
	Logger logger = LoggerFactory.getLogger(AllocationHistoryServiceImpl.class);
	
	//to get subject from the token
	public String extractUsername(String token) {
		return extractClaim(token, Claims::getSubject);
	}
	
	//to parse the token
	private Claims extractAllClaims(String token) {
		try {
			return Jwts
					.parserBuilder()
					.setSigningKey(getSignInKey())
					.build()
					.parseClaimsJws(token)
					.getBody();
					
		}catch(ExpiredJwtException e){
			logger.error("Jwt expired");
			throw e;
		}
		
		
	}
	
	public <T> T extractClaim(String token,Function<Claims, T>claimsResolver) {
	
		final Claims claims=extractAllClaims(token);
		return claimsResolver.apply(claims);
	
	}
	
	//generating token with iven userdetails
	public String generateToken(UserDetails userDetails) {
		return generateToken(new HashMap<>(),userDetails);
	}
	
	//how to generate token
	public String generateToken(
		Map<String,Object> extraClaims,
		UserDetails userDetails
	) {
		return Jwts
				.builder()
				.setClaims(extraClaims)
				.setSubject(userDetails.getUsername())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis()+1000*60*24*60))
				.signWith(getSignInKey(),SignatureAlgorithm.HS256)
				.compact();
	}
	
	//to check is token expired
	public boolean isTokenValid(String token, UserDetails userDetails) {
		final String username = extractUsername(token);
		return(username.equals(userDetails.getUsername())) && !isTokenExpired(token);
	}
	
	//to check is token expired
	private boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}
	
	//to extraxt expiration date
	private Date extractExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
	}

	private java.security.Key getSignInKey() {
		byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
		return Keys.hmacShaKeyFor(keyBytes);
	}
	

}
