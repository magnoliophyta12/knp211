package com.ctb.rest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(
				authorizeRequests -> authorizeRequests.requestMatchers("/contacts/**").authenticated() // защита URL
																										// /contacts
						.anyRequest().permitAll() // доступ к другим URL разрешён всем
		).formLogin(formLogin -> formLogin.loginPage("/login") // URL для страницы входа
				.defaultSuccessUrl("/contacts", true).permitAll()).logout(logout -> logout.permitAll());
		
		

		return http.build();
	}
	

	@Bean
	UserDetailsService userDetailsService() {
	    UserDetails alex = User.builder().username("Alex").password(passwordEncoder().encode("1234")).roles("ADMIN").build();
	    UserDetails oleg = User.builder().username("Oleg").password(passwordEncoder().encode("oleg")).roles("USER").build();
	    return new InMemoryUserDetailsManager(alex, oleg);
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}