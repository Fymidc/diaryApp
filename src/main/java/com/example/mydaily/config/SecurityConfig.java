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
	    		.antMatchers(HttpMethod.POST,"/questions")
	    		.permitAll()
	    		.antMatchers(HttpMethod.GET,"/questions")
	    		.permitAll()
	    		.antMatchers(HttpMethod.GET,"/questions/**")
	    		.permitAll()
	    		.antMatchers(HttpMethod.PUT,"/questions/*")
	    		.permitAll()
	    		.antMatchers(HttpMethod.GET,"/answers/**")
	    		.permitAll()
	    		.antMatchers(HttpMethod.GET,"/posts")
	    		.permitAll()
	    		.antMatchers(HttpMethod.GET,"/posts/**")
	    		.permitAll()
	    		.antMatchers(HttpMethod.GET,"/questions/perday")
	    		.permitAll()
	    		.antMatchers(HttpMethod.GET,"/comments")
	    		.permitAll()
	    		.antMatchers(HttpMethod.GET,"/comments/**")
	    		.permitAll()
	    		.antMatchers(HttpMethod.GET,"/comments/diary/**")
	    		.permitAll()
	    		.antMatchers(HttpMethod.GET,"/likes")
	    		.permitAll()
	    		.antMatchers(HttpMethod.DELETE,"/likes/*")
	    		.permitAll()
	    		.antMatchers(HttpMethod.POST,"/likes")
	    		.permitAll()
	    		.antMatchers(HttpMethod.GET,"/likes/**")
	    		.permitAll()
	    		.antMatchers(HttpMethod.DELETE,"/comments/*")
	    		.permitAll()
	    		.antMatchers(HttpMethod.POST,"/comments/**")
	    		.permitAll()
	    		.antMatchers(HttpMethod.GET,"/diaries")
	    		.permitAll()
	    		.antMatchers(HttpMethod.GET,"/diaries/**")
	    		.permitAll()
	    		.antMatchers(HttpMethod.PUT,"/diaries/**")
	    		.permitAll()
	    		.antMatchers(HttpMethod.GET,"/user")
	    		.permitAll()
	    		.antMatchers(HttpMethod.GET,"/user/**")
	    		.permitAll()
	    		.anyRequest().authenticated();
	    		
	    	
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
