package com.seba.springboot.ggym.app.services.impl;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.seba.springboot.ggym.app.entities.UserEntity;
import com.seba.springboot.ggym.app.services.JwtService;
import com.seba.springboot.ggym.app.utils.DateUtils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtServiceImpl implements JwtService {
	
	@Value("${token.signing.key}")
	private String jwtSigningKey;

	@Override
	public String extractUserName(String token) {
		return extractClaims(token, Claims::getSubject);
	}

	@Override
	public String generateToken(UserEntity userDetails) {
		return generateToken(new HashMap<>(), userDetails);
	}

	@Override
	public boolean isTokenValid(String token, UserDetails userDetails) {
		final String userName = extractUserName(token);
		return userName.equals(userDetails.getUsername()) && !isTokenExpired(token);
	}
	
	private <T> T extractClaims(String token, Function<Claims, T> claimsResolvers) {
		final Claims claims = extractAllClaims(token);
		return 	claimsResolvers.apply(claims);
	}
	
	private Claims extractAllClaims(String token) {
		return Jwts.parserBuilder()
				.setSigningKey(getSigningKey())
				.build()
				.parseClaimsJws(token)
				.getBody();
	}
	
	private Key getSigningKey() {
		byte[] keyBytes = Decoders.BASE64.decode(jwtSigningKey);
		return Keys.hmacShaKeyFor(keyBytes);
	}
	
	private String generateToken(Map<String, Object> extraClaims, UserEntity userDetails) {
		return Jwts.builder()
				.setClaims(extraClaims)
				.setSubject(userDetails.getUsername())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(DateUtils.expirationDate())
				.signWith(getSigningKey(), SignatureAlgorithm.HS256)
				.compact();
	}
	
	private boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}

	private Date extractExpiration(String token) {
		return extractClaims(token, Claims::getExpiration);
	}
}
