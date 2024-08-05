package com.capstone.medicare.service;

import java.util.Set;

import com.capstone.medicare.model.User;
import com.capstone.medicare.model.UserRole;


public interface UserService {
	
	public User createUser(User user, Set<UserRole> userRole) throws Exception;
	
	public User getUser(String username);
	
	public void deleteUser(Long userId);

}