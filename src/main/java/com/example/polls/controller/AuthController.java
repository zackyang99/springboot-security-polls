package com.example.polls.controller;

import java.io.IOException;
import java.net.URI;
import java.time.Instant;
import java.util.Collections;

import javax.mail.MessagingException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.polls.config.ParameterConfig;
import com.example.polls.exception.AppException;
import com.example.polls.exception.BadRequestException;
import com.example.polls.model.Role;
import com.example.polls.model.RoleName;
import com.example.polls.model.User;
import com.example.polls.model.profile.EmailVerification;
import com.example.polls.payload.ApiResponse;
import com.example.polls.payload.JwtAuthenticationResponse;
import com.example.polls.payload.LoginRequest;
import com.example.polls.payload.SignUpRequest;
import com.example.polls.repository.RoleRepository;
import com.example.polls.repository.UserRepository;
import com.example.polls.repository.profile.EmailVerificationRepository;
import com.example.polls.security.JwtTokenProvider;
import com.example.polls.service.EmailService;
import com.example.polls.util.Utils;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	JwtTokenProvider tokenProvider;
	
	@Autowired
	ParameterConfig pConfig;
	
	@Autowired
	EmailVerificationRepository evRepo;
	
	@Autowired
	EmailService emailService;
	
	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						loginRequest.getUsernameOrEmail(),
						loginRequest.getPassword()
				)
		);
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		String jwt = tokenProvider.generateToken(authentication);
		
		return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
	}
	
	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return new ResponseEntity(new ApiResponse(false, "Username is already taken!"), HttpStatus.BAD_REQUEST);
			
		}
		
		if(userRepository.existsByEmail(signUpRequest.getEmail())) {
            return new ResponseEntity(new ApiResponse(false, "Email Address already in use!"),
                    HttpStatus.BAD_REQUEST);
        }
		
		// creating user's account
		User user = new User(signUpRequest.getName(), signUpRequest.getUsername(),
				signUpRequest.getEmail(), signUpRequest.getPassword());
		
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		
		Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
				.orElseThrow(() -> new AppException("User Role not set"));
		
		user.setRoles(Collections.singleton(userRole));
		
		User result = userRepository.save(user);
		
		URI location = ServletUriComponentsBuilder
				.fromCurrentContextPath().path("/api/users/{username}")
				.buildAndExpand(result.getUsername()).toUri();
		
		return ResponseEntity.created(location).body(new ApiResponse(true, "User registered successfully"));
	}
	
	@GetMapping("/sendCode")
	public void sendVerificationCode(@RequestParam(value="email", required=true) String email) {
		String code = Utils.generateCode();
		try {
			EmailVerification ev = new EmailVerification();
			ev.setEmail(email);
			ev.setCode(code);
			evRepo.save(ev);
			emailService.sendVerificationCodeUsingSendGrid(pConfig.getEmailFrom(), email, pConfig.getEmailSubject(), code);;
		} catch (IOException ioe) {
			throw new AppException("Failed to send code", ioe);
		}
	}
	
	@GetMapping("/verifyCode")
	public boolean verifyCode(@RequestParam(value="email", required=true) String email,
			@RequestParam(value="code", required=true) String code) {
		EmailVerification ev = evRepo.findById(email)
				.orElseThrow(() -> new BadRequestException("Unregistered email"));
		
		return code != null && code.compareTo(ev.getCode()) == 0 &&
				(Instant.now().getEpochSecond() - ev.getUpdatedAt().getEpochSecond() <= pConfig.getVerificationInterval());
	}
}
