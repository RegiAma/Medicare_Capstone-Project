package com.capstone.medicare.model;

import org.springframework.security.core.GrantedAuthority;

public class Auth implements GrantedAuthority{
	
	private static final long serialVersionUID = 1L;
	private String authority;
	
	public Auth(String authority) {
		super();
		this.authority = authority;
	}

	public Auth() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getAuthority() {
		// TODO Auto-generated method stub
		return this.authority;
	}

}

