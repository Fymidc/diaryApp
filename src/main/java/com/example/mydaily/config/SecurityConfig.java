package com.example.mydaily.config;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;

import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.example.mydaily.auth.JwtAuthenticationEntryPoint;
import com.example.mydaily.auth.JwtTokenFilter;
import com.example.mydaily.business.concretes.UserDetailServiceImp;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Autowired
	private UserDetailServiceImp detailServiceImp;
	
	@Autowired
	private JwtAuthenticationEntryPoint handler;
		
		 @Bean
		    public JwtTokenFilter authenticationJwtTokenFilter() {
		        return new JwtTokenFilter();
		    }
	    
		@Bean
		public AuthenticationManager authenticationManager(
		        AuthenticationConfiguration authConfig) throws Exception {
		    return authConfig.getAuthenticationManager();
		}

		@Bean
		public DaoAuthenticationProvider authenticationProvider() {
		    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		     
		    authProvider.setUserDetailsService(detailServiceImp);
		    authProvider.setPasswordEncoder(encoder());
		 
		    return authProvider;
		}
		
		  @Bean
		    public BCryptPasswordEncoder encoder() {
		    	return new BCryptPasswordEncoder();
		    }
	    
		
	    @Bean
	    public SecurityFilterChain filter(HttpSecurity http)throws Exception{
	    	http
	    		.cors()
	    		.and()
	    		.csrf().disable()
	    		.exceptionHandling().authenticationEntryPoint(handler).and()
	    		.authenticationProvider(authenticationProvider())
	    		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
	    		.authorizeRequests()
	    		.antMatchers("/auth/**")
	    		
	    		.permitAll()
	    		.anyRequest().permitAll();
	    		
	    	
	    	http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);

	    	return http.build();
	    }
	    
	   
	    
	    @Bean
	    public CorsFilter corsFilter() {
	        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	        CorsConfiguration config = new CorsConfiguration();
	        config.setAllowCredentials(true);
	        config.addAllowedOriginPattern("*");
	        config.addAllowedHeader("*");
	        config.addAllowedMethod("OPTIONS");
	        config.addAllowedMethod("HEAD");
	        config.addAllowedMethod("GET");
	        config.addAllowedMethod("PUT");
	        config.addAllowedMethod("POST");
	        config.addAllowedMethod("DELETE");
	        config.addAllowedMethod("PATCH");
	        source.registerCorsConfiguration("/**", config);
	        return new CorsFilter(source);
	    }
	   
	   
}
