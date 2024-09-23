package com.example.lab.service.securityServices;

import java.security.Key;
import java.time.Instant;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.lab.model.Token;
import com.example.lab.model.User;
import com.example.lab.service.TokenService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
	//config
	private static final String SECRET_KEY="LFO302h++zFOWfdcJWuCS+kpLVv4QS7D/AjAj5f4apo=";
	public static final Long TIME_ALIVE = (long) 3600000;//1h
	public static final Long ACTIVE_TIME_ALIVE = (long) 86400000;//1d
	
	@Autowired
	private TokenService tokenService;
	
	public String generateToken(User user) {
		return Jwts.builder().setSubject(user.getUsername()).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + TIME_ALIVE ))
				.signWith(getSignInKey(), SignatureAlgorithm.HS256).compact();
	}

	public String generateActiveToken(User user) {
		return Jwts.builder().setSubject(user.getUsername()).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + ACTIVE_TIME_ALIVE ))
				.signWith(getSignInKey(), SignatureAlgorithm.HS256).compact();
	}

	public String extractUsername(String token) {
		Token t = tokenService.findByToken(token);
		if(t.equals(null))
			return null;
		Claims claims = extractClaims(token);
		if (claims != null) {
			Date expirationTime = claims.getExpiration();
			boolean isExpired = expirationTime.before(Date.from(Instant.now()));
			if (!isExpired) {
				return claims.getSubject();
			} else {
				tokenService.setExpired(token);
				return null;
			}
		}
		return null;
	}

	private Key getSignInKey() {
		byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
		return Keys.hmacShaKeyFor(keyBytes);
	}

	private Claims extractClaims(String token) {
		return Jwts.parserBuilder().setSigningKey(getSignInKey()).build().parseClaimsJws(token).getBody();
	}

}
