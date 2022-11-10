package com.estockmarketapp.securityservice.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.estockmarketapp.securityservice.dto.AuthRequest;
import com.estockmarketapp.securityservice.util.JwtUtil;

@RestController
public class SecurityAPI {

	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@GetMapping("/")
	public String loginMsg() { 
		return "Log in was Succesfully ";
	}

	@PostMapping("/auth/login")
	public ResponseEntity<String> generateToken(@RequestBody AuthRequest authRequest) throws Exception {
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authRequest.getUserName(),authRequest.getPassword())
					);
		}catch (Exception ex) {
			return new ResponseEntity<String>("invalid user/password",HttpStatus.BAD_REQUEST);
			
			//throw new Exception("invalid user/password");
		}	
		
		return new ResponseEntity<String>(jwtUtil.generateToken(authRequest.getUserName()),HttpStatus.OK);
	}
}
