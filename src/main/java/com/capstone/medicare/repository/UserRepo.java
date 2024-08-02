package com.capstone.medicare.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capstone.medicare.model.User;


public interface UserRepo extends JpaRepository<User, Long>{
	
	public User findByUsername(String username);

}
