package com.example.polls.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.polls.model.profile.Profile;
import com.example.polls.payload.profile.ProfileRequest;
import com.example.polls.repository.UserRepository;
import com.example.polls.repository.profile.ProfileRepository;
import com.example.polls.security.UserPrincipal;
import com.example.polls.util.ProfileModelMapper;

@Service
public class ProfileService {
	@Autowired ProfileRepository profileRepo;
	@Autowired ProfileModelMapper pmm;
	@Autowired UserRepository userRepo;
	
	public void saveOrUpdateProfile(UserPrincipal currentUser, ProfileRequest pr) {
		Profile profile = pmm.mapProfileRequestToProfile(pr);
//		User user = userRepo.findById(currentUser.getId())
//				.orElseThrow(() -> new ResourceNotFoundException("User", "userId", currentUser.getId()));
	
		profile.setId(currentUser.getId());
//		profile.setUser(user);		
		profileRepo.save(profile);
	}
}
