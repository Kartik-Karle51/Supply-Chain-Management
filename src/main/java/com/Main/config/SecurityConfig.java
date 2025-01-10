package com.Main.config;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

//import com.Main.Service.CustomUserService;

//import com.Main.Service.UserService;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

//	
//	@Autowired
//	CustomUserService service;
	
	 @Bean
	    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	        return http
	                .csrf(Customizer -> Customizer.disable())
	                .authorizeHttpRequests(request -> request
	                		.requestMatchers("/admin/**").hasRole("ADMIN")
	                		.anyRequest().permitAll())
	                .formLogin(Customizer.withDefaults())
	                .httpBasic(Customizer.withDefaults())
	      
	                		
	                .build();
	    }

	 
	 @Bean 
	 public UserDetailsService usd() {
		 UserDetails user1=User
				 .withDefaultPasswordEncoder()
				 .username("admin")
				 .password("KK@123")
				 .roles("ADMIN")
				 .build();
		 
		 return new InMemoryUserDetailsManager(user1);
	 }
	
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    public AuthenticationProvider authenticationProvider() {
//    	DaoAuthenticationProvider dAP=new DaoAuthenticationProvider();
//    	dAP.setUserDetailsService(service);
//    	dAP.setPasswordEncoder(passwordEncoder());
//    	return dAP;
//    }


    
}
