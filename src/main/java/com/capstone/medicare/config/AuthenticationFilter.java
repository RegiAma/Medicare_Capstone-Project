package com.capstone.medicare.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.capstone.medicare.service.imp.UserDetailsServiceImp;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AuthenticationFilter extends OncePerRequestFilter{

	@Autowired
	private UserDetailsServiceImp userDetailsServiceImpl;

	@Autowired
	private Utils Utils;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		final String requestTokenHeader = request.getHeader("Authorization");
		System.out.printf("requestTokenHeader: ",  requestTokenHeader);
		String username = null;
		String Token = null;

		if(requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
			//yes

			Token = requestTokenHeader.substring(7);
			try {
				username = this.Utils.extractUsername(Token);
			} catch(ExpiredJwtException e) {
				e.printStackTrace();
				System.out.println("jwt token has expired");
			}catch(Exception e) {
				e.printStackTrace();
				System.out.println("error");
			}
		}else {
			System.out.println("Invlaid token, not start with bearer string");
		}

		//validated

		if(username != null && SecurityContextHolder.getContext().getAuthentication()==null) {
			final UserDetails userDetails = this.userDetailsServiceImpl.loadUserByUsername(username);

			if(this.Utils.validateToken(Token, userDetails)) {
				//token is valid

				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken= new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
				usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));	
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}
		}else {
			System.out.println("Token is not valid");
		}
		filterChain.doFilter(request, response);

	}

}
