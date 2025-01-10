//package com.Main.Service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import com.Main.Model.*;
//import com.Main.Repository.UserRepository;
//
//@Service
//public class CustomUserService implements UserDetailsService{
//
//	@Autowired
//		    private UserRepository userRepository;
//
//	 @Override
//	    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//	        // First, check if the user exists in the database
//	        AllUsers user = userRepository.findUserByName(username);
//
//	        // If user not found, simulate default admin
//	        if (user == null) {
//	            if (username.equals("admin")) {
//	                // Creating an admin user (replace with actual data from DB if possible)
//	                user = new AllUsers();
//	                user.setName("admin");
//	                user.setPassword("KK@123"); // bcrypt("KK@123")
//	                user.setRole("ADMIN");
//	            } else {
//	                throw new UsernameNotFoundException("User not found");
//	            }
//	        }
//	        return user;
//	    }
//
//}
