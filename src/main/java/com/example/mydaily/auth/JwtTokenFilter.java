package com.example.mydaily.auth;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.mydaily.business.concretes.UserDetailServiceImp;

@Component
public class JwtTokenFilter extends OncePerRequestFilter {
	
	@Autowired
    TokenManager jwtTokenProvider;

	@Autowired
	UserDetailServiceImp userDetailsService;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			String jwtToken = extractJwtFromRequest(request);
			
			if(StringUtils.hasText(jwtToken)&& jwtTokenProvider.validateToken(jwtToken)) {
				Long id = jwtTokenProvider.getUserIdFromJwt(jwtToken);
				UserDetails user =userDetailsService.loadUserById(id);
				if(user != null) {
					UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(user,null, user.getAuthorities());
					auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					SecurityContextHolder.getContext().setAuthentication(auth);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			return;
		}
		filterChain.doFilter(request, response);
	}
	

	  private String extractJwtFromRequest(HttpServletRequest request){
	        String bearer = request.getHeader("Authorization");
	        if(StringUtils.hasText(bearer)&& bearer.startsWith("Bearer ")){
	            return bearer.substring("Bearer".length()+1);
	        }
	        return null;
	    }
	  
}
