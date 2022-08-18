package com.example.mydaily.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.mydaily.auth.JwtAuthenticationEntryPoint;
import com.example.mydaily.auth.JwtTokenFilter;
import com.example.mydaily.business.concretes.UserDetailServiceImp;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	private UserDetailServiceImp userDetailsService;
	
	private JwtAuthenticationEntryPoint handler;
	
    public SecurityConfig(UserDetailServiceImp userDetailsService, JwtAuthenticationEntryPoint handler) {
        this.userDetailsService = userDetailsService;
        this.handler = handler;
    }
	
    	@Bean
	    public JwtTokenFilter jwtAuthenticationFilter() {
	    	return new JwtTokenFilter();
	    }
	    
    	 @Bean
    	    AuthenticationManager authenticationManager(AuthenticationManagerBuilder builder) throws Exception {
    	        return builder.userDetailsService(userDetailsService).passwordEncoder(encoder()).and().build();
    	    }
	    

	    @Autowired
	    public void configurePasswordEncoder(AuthenticationManagerBuilder builder) throws Exception {
	    	builder.userDetailsService(userDetailsService).passwordEncoder(encoder());
	    }

	    @Bean
	    public BCryptPasswordEncoder encoder() {
	    	return new BCryptPasswordEncoder();
	    }
	    
	    @Bean
	    public SecurityFilterChain filter(HttpSecurity http)throws Exception{
	    	http.cors().and().csrf().disable()
	    		.exceptionHandling().authenticationEntryPoint(handler).and()
	    		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
	    		.authorizeRequests()
	    		.antMatchers("/auth/**")
	    		.permitAll()
	    		.anyRequest().authenticated();
	    	
	    	http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
	    	
	    	return http.build();
	    }
	   
}
