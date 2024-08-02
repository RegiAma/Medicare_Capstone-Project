package com.capstone.medicare.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.capstone.medicare.config.Utils;
import com.capstone.medicare.model.Request;
import com.capstone.medicare.model.Response;
import com.capstone.medicare.service.imp.UserDetailsServiceImp;

@RestController
@CrossOrigin("http://localhost:4200")
public class AuthenticationContr {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsServiceImp userDetailsServiceImp;

    @Autowired
    private Utils Utils;

    // Generate token
    @PostMapping("/generate-token")
    public ResponseEntity<?> generateToken(@RequestBody Request jwtRequest) throws Exception {
        try {
            authenticate(jwtRequest.getUsername(), jwtRequest.getPassword());
        } catch (UsernameNotFoundException e) {
            e.printStackTrace();
            throw new Exception("User not found");
        }

        UserDetails userDetails = this.userDetailsServiceImp.loadUserByUsername(jwtRequest.getUsername());
        String token = this.Utils.generateToken(userDetails);

        return ResponseEntity.ok(new Response(token));
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER DISABLED " + e.getMessage());
        } catch (BadCredentialsException e) {
            throw new Exception("Invalid Credentials " + e.getMessage());
        }
    }

    // Return details of the current user
    @GetMapping("/current-user")
    public UserDetails getCurrentUser(Principal principal) {
        return this.userDetailsServiceImp.loadUserByUsername(principal.getName());
    }
}
