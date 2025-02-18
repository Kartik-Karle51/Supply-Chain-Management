package com.Main.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.Main.Model.*;
import com.Main.Repository.UserRepository;

@Service
public class CustomUserService implements UserDetailsService{

	@Autowired
		    private UserRepository userRepository;

	 @Override
	    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	      
	        return userRepository.findByName(username);
	    }

}
