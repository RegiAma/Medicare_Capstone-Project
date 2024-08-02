package com.capstone.medicare.service.imp;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstone.medicare.exceptions.UserFoundExcept;
import com.capstone.medicare.model.User;
import com.capstone.medicare.model.UserRole;
import com.capstone.medicare.repository.RoleRepo;
import com.capstone.medicare.repository.UserRepo;
import com.capstone.medicare.service.UserService;
@Service
public class UserServiceImp implements UserService {

	@Autowired
	private UserRepo userRepository;
	
	@Autowired
	private RoleRepo roleRepository;
	
	//creating user
	@Override
	public User createUser(User user, Set<UserRole> userRoles) throws Exception {
		
		User local = this.userRepository.findByUsername(user.getUsername());
		
		if(local != null) {
			System.out.println("User is already present!!");
			throw new UserFoundExcept();
		}else {
			for(UserRole ur : userRoles) {
				roleRepository.save(ur.getRole());
			}
			
			user.getUserRoles().addAll(userRoles);
			local = this.userRepository.save(user);
			
		}
		return local;
	}

	//To get user by username
	@Override
	public User getUser(String username) {
		// TODO Auto- method stub
		return this.userRepository.findByUsername(username);
	}

	@Override
	public void deleteUser(Long userId) {
		this.userRepository.deleteById(userId);	
	}
	
	
	
	
 

}

