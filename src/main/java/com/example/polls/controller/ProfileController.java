package com.example.polls.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.polls.exception.BadRequestException;
import com.example.polls.payload.profile.ProfileRequest;
import com.example.polls.security.CurrentUser;
import com.example.polls.security.UserPrincipal;
import com.example.polls.service.ProfileService;

@RestController
@RequestMapping("/profile")
public class ProfileController {
	@Autowired ProfileService profileService;
	
	@PostMapping("/save")
	public boolean saveProfile(@CurrentUser UserPrincipal currentUser, @Valid @RequestBody ProfileRequest profileRequest, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {  
          String errMsg="";
          for(FieldError err: bindingResult.getFieldErrors()) {  
              errMsg += "Invalid " + err.getField();
          }  
          throw new BadRequestException(errMsg);
      }  
		
		profileService.saveOrUpdateProfile(currentUser, profileRequest);
		
		return true;
	}

}