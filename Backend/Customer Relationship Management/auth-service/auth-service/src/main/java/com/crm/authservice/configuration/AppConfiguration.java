package com.crm.authservice.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.crm.authservice.filter.TokenValidatorFilter;
import com.crm.authservice.service.ManagerService;

@Configuration
public class AppConfiguration {

	@Autowired
	PasswordEncoder encoder;
	@Autowired
	ManagerService service;

	@Autowired
	private JwtAuthenticationEntryPoint entryPoint;

	@Bean
	public DaoAuthenticationProvider daoAuthProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(service);
		provider.setPasswordEncoder(encoder);
		return provider;
	}

	@Bean
	SecurityFilterChain defaultFilterChain(HttpSecurity http) throws Exception {

		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		http.cors().and().csrf().disable();

		http.authorizeRequests().antMatchers("/register", "/login", "/hello").permitAll().anyRequest().authenticated();
		http.authenticationProvider(daoAuthProvider());

		http.exceptionHandling().authenticationEntryPoint(entryPoint);
		http.addFilterBefore(new TokenValidatorFilter(), UsernamePasswordAuthenticationFilter.class);
	//	http.addFilterBefore(new CorsFilter(), ChannelProcessingFilter.class);
		return http.build();
	}

	@Bean
	public AuthenticationManager authenticationManagerBean(AuthenticationConfiguration configuration) throws Exception {
		return configuration.getAuthenticationManager();
	}

}
