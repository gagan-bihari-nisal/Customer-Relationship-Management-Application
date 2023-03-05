package com.crm.customerservice.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.crm.customerservice.filter.TokenValidatorFilter;

@Configuration
public class AppSecurityConfig {

	@Autowired
	private JwtAuthenticationEntryPoint entryPoint;

	@Bean
	SecurityFilterChain defaultConfig(HttpSecurity http) throws Exception {
		http.csrf().disable();
		
		http.cors();
		
		
		http.authorizeRequests()
		.antMatchers("/hello").permitAll()
		.anyRequest()
		.authenticated(); 

		http.addFilterBefore(new TokenValidatorFilter(), UsernamePasswordAuthenticationFilter.class);
		http.exceptionHandling().authenticationEntryPoint(entryPoint).and().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		return http.build();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
