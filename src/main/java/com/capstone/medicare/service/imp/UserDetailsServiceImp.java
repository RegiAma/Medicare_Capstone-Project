package com.capstone.medicare.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.capstone.medicare.model.User;
import com.capstone.medicare.repository.UserRepo;

@Service
public class UserDetailsServiceImp implements UserDetailsService{

	@Autowired
	private UserRepo userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = this.userRepository.findByUsername(username);
		
		if(user == null) {
			System.out.println("User not found");
			throw new UsernameNotFoundException("No user found");
		}
		return user;
	}

}
