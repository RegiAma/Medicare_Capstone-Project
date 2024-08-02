package com.capstone.medicare.controller;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.simplilearn.Medicare_App.exceptions.UserFoundException;
import com.simplilearn.Medicare_App.exceptions.UserNotFoundException;
import com.simplilearn.Medicare_App.model.Role;
import com.simplilearn.Medicare_App.model.User;
import com.simplilearn.Medicare_App.model.UserRole;
import com.simplilearn.Medicare_App.service.UserService;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping(path = "/user")
public class UserContr {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@GetMapping("/test")
    public String test() {
        return "Welcome to Medicare";
    }
		
	@PostMapping("/")
	public User createUser(@RequestBody User user) throws Exception {
		
		
		//encoding password with bcryptpasswordencoder
		user.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));
		
		
		Set<UserRole> roles = new HashSet<UserRole>();	
		Role role = new Role(25L, "NORMAL");	
		UserRole userRole = new UserRole();
		userRole.setUser(user);
		userRole.setRole(role);		
		roles.add(userRole);
		user.setProfile("default.png");
		return this.userService.createUser(user, roles);
		
	}
	
	@GetMapping("/{username}")
	public User getUser(@PathVariable(value = "username") String username) {	
		return this.userService.getUser(username);		
	}
	
	@DeleteMapping("{userId}")
	public void deleteUser(@PathVariable("userId") Long userId) {
		this.userService.deleteUser(userId);
	}

	@ExceptionHandler(UserFoundException.class)
	public ResponseEntity<?> exceptionHandler(UserFoundException ex){
		return ResponseEntity.ok(ex.getMessage());

	}
}