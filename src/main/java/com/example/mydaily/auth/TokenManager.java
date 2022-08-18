package com.example.mydaily.auth;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Service
public class TokenManager {

	@Value("${mydaily.app.secret}")
	private String APP_SECRET;
	
	@Value("${mydaily.expires.in}")
	private long EXPIRES_IN;
	
	public String generateToken(Authentication auth){
		JwtUserDetails userdetails = (JwtUserDetails) auth.getPrincipal(); 
		Date expireDate = new Date(new Date().getTime() + EXPIRES_IN);
		return Jwts.builder().setSubject(Long.toString(userdetails.getId()))
				.setIssuer("fymidc")
				.setIssuedAt(new Date())
				.setExpiration(expireDate)
				.signWith(SignatureAlgorithm.HS512, APP_SECRET).compact();
	}
	
	 Long getUserIdFromJwt(String token){
	        //appsecret kullanarak keyden useri buluyoruz geri giderek
	        Claims claims = Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(token).getBody();
	        return  Long.parseLong(claims.getSubject());

	    }

	    //token doğrumu kontrol

	    boolean validateToken(String token){
	        try{//parse ediyorsa doğru keydir
	            Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(token);
	            return !isTokenExpired(token);
	        }catch(SignatureException e){
	            return false;
	        }catch(MalformedJwtException e){
	            return false;
	        }catch(ExpiredJwtException e){
	            return false;
	        }catch(UnsupportedJwtException e){
	            return false;
	        }catch(IllegalArgumentException e){
	            return false;
	        }

	    }

	    private boolean isTokenExpired(String token){
	        Date  expiration = Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(token).getBody().getExpiration();
	        return expiration.before(new Date());
	    }
}
