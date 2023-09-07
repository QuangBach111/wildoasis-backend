package com.example.backend_v2.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtil {
	private static final String SECRET_KEY = "pOjBNk6j9c3q24yF3gUzsW8kZ0tMaLqygPWUMYfCQNscy7UzplFgwkH1gO1thaIX";

	public <T extends UserDetails> String generateToken(T t, Map<String, Object> extraClaims) {
		return Jwts.builder()
				.setClaims(extraClaims)
				.setSubject(t.getUsername())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 3))
				.signWith(getSignInKey(), SignatureAlgorithm.HS256)
				.compact();
	}
	public String generateTokenWithName(String username, Map<String, Object> extraClaims) {
		return Jwts.builder()
				.setClaims(extraClaims)
				.setSubject(username)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 3))
				.signWith(getSignInKey(), SignatureAlgorithm.HS256)
				.compact();
	}

	public String generateTokenWithName(String username) {
		return Jwts.builder()
				.setSubject(username)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 3))
				.signWith(getSignInKey(), SignatureAlgorithm.HS256)
				.compact();
	}
	public <T extends UserDetails> String generateToken(T t) {
		return Jwts.builder()
				.setSubject(t.getUsername())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 3))
				.signWith(getSignInKey(), SignatureAlgorithm.HS256)
				.compact();
	}

	public String extractUsername(String token) {
		return extractClaim(token, Claims::getSubject);
	}

	public Long extractId(String token) {
		return extractClaim(token, claims -> claims.get("id", Long.class));
	}

	public boolean isTokenValid(String token, UserDetails userDetails) {
		final String username = extractUsername(token);
		return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
	}

	public boolean isTokenExpired(String token) {
		return extracExpiration(token).before(new Date());
	}

	private Date extracExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
	}

	private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}

	private Key getSignInKey() {
		byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
		return Keys.hmacShaKeyFor(keyBytes);
	}

	private Claims extractAllClaims(String token) {
		return Jwts
				.parserBuilder()
				.setSigningKey(getSignInKey())
				.build()
				.parseClaimsJws(token)
				.getBody();
	}
}