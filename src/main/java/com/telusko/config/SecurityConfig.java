package com.telusko.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }
	@Autowired
	private JwtFilter jwtFilter;
	
	@Bean
	 SecurityFilterChain securityFilterChain(HttpSecurity http)throws Exception
	{
//		http.csrf(customizer ->customizer.disable()); //this is equal to ____
//		http.authorizeHttpRequests(request -> request.anyRequest().authenticated());//it will hide the login page 
//		http.formLogin(Customizer.withDefaults());//it will give you the login page
//		http.httpBasic(Customizer.withDefaults());
//		http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		
//		______ //here to
		//Customizer<CsrfConfigurer<HttpSecurity>> custCsrf=new Customizer<CsrfConfigurer<HttpSecurity>>() {
//			
//			@Override
//			public void customize(CsrfConfigurer<HttpSecurity> customizer) {
//				// TODO Auto-generated method stub
//				customizer.disable();
//				
//			}
//		};//____ here
//		http.csrf(custCsrf); //____ this method and lines
		
	return http.csrf(customizer ->customizer.disable())
//			.authorizeHttpRequests(request -> request.anyRequest().authenticated())
			.authorizeHttpRequests(request -> request
					.requestMatchers("register", "login")
					.permitAll()
					.anyRequest().authenticated())

			.httpBasic(Customizer.withDefaults())
			.sessionManagement(session -> 
			session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			.addFilterBefore(jwtFilter,UsernamePasswordAuthenticationFilter.class)
		.build();
	}

//	@Bean
//	public UserDetailsService userDetailsService()
//	{
//	
//		UserDetails user1= User 
//				.withDefaultPasswordEncoder()
//				.username("sanjay")
//				.password("12345")
//				.roles("USER")
//				.build();
//		UserDetails user2= User 
//				.withDefaultPasswordEncoder()
//				.username("vijay")
//				.password("1234")
//				.roles("ADMIN")
//				.build();
//		
//				
//		return new InMemoryUserDetailsManager(user1,user2);
//	}
	
	@Bean
	AuthenticationProvider authenticationProvider()
	{
		DaoAuthenticationProvider provider=new DaoAuthenticationProvider();
//		provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
		provider.setPasswordEncoder(new BCryptPasswordEncoder(12));
		provider.setUserDetailsService(userDetailsService);
		return provider;
	}
	
	@Bean 
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception
	{
		return config.getAuthenticationManager();
		
	}
	
}
