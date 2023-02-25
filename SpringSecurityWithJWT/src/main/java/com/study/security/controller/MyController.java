package com.study.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.study.security.model.AuthenticationRequest;
import com.study.security.model.AuthenticationResponce;
import com.study.security.services.MyUserDetailsService;
import com.study.security.utils.JWTUtils;

@RestController
public class MyController {
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private MyUserDetailsService userDetailsService;

	@Autowired
	private JWTUtils jwtUtils;

	@RequestMapping({ "/hello" })
	public String Hello() {
		return "Hello";
	}

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest)
			throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					authenticationRequest.getUserName(), authenticationRequest.getPassword()));
		} catch (BadCredentialsException e) {
			throw new Exception("Bad Username Password", e);
		}
		final UserDetails userDetails=userDetailsService.loadUserByUsername(authenticationRequest.getUserName());
		final String jwt= jwtUtils.generateToken(userDetails);
		return ResponseEntity.ok(new AuthenticationResponce(jwt));
	}
}
