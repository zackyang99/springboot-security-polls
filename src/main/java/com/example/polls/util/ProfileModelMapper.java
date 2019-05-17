package com.example.polls.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.polls.exception.ResourceNotFoundException;
import com.example.polls.model.profile.City;
import com.example.polls.model.profile.Country;
import com.example.polls.model.profile.Country2;
import com.example.polls.model.profile.Education;
import com.example.polls.model.profile.Experience;
import com.example.polls.model.profile.Major;
import com.example.polls.model.profile.Profile;
import com.example.polls.model.profile.State;
import com.example.polls.model.profile.University;
import com.example.polls.payload.profile.EducationRequest;
import com.example.polls.payload.profile.ExperienceRequest;
import com.example.polls.payload.profile.ProfileRequest;
import com.example.polls.repository.profile.CityRepository;
import com.example.polls.repository.profile.Country2Repository;
import com.example.polls.repository.profile.CountryRepository;
import com.example.polls.repository.profile.MajorRepository;
import com.example.polls.repository.profile.StateRepository;
import com.example.polls.repository.profile.UniversityRepository;

@Component
public class ProfileModelMapper {
	@Autowired CountryRepository countryRepo;
	@Autowired StateRepository stateRepo;
	@Autowired CityRepository cityRepo;
	@Autowired Country2Repository country2Repo;
	@Autowired UniversityRepository universityRepo;
	@Autowired MajorRepository majorRepo;
	public Profile mapProfileRequestToProfile(ProfileRequest profileRequest) {
		Profile profile = new Profile();
//		profile.setId(profileRequest.getProfileId());
		Country country = countryRepo.findById(profileRequest.getCountryId())
				.orElseThrow(() -> new ResourceNotFoundException("Country", "countryId", profileRequest.getCountryId()));
		profile.setCountry(country);
		
		State state = stateRepo.findById(profileRequest.getStateId())
				.orElseThrow(() -> new ResourceNotFoundException("State", "stateId", profileRequest.getStateId()));
		profile.setState(state);
		
		City city = cityRepo.findById(profileRequest.getCityId())
				.orElseThrow(() -> new ResourceNotFoundException("City", "cityId", profileRequest.getCityId()));
		profile.setCity(city);
		
		List<EducationRequest> educationRequest = profileRequest.getEducationRequest();
		educationRequest.forEach(er -> {
			profile.addEducation(mapEducationRequestToEducation(er));
		});
		
		List<ExperienceRequest> experienceRequest = profileRequest.getExperienceRequest();
		experienceRequest.forEach(er -> {
			profile.addExperience(mapExperienceRequestToExperience(er));
		});
		
		return profile;
	}
	
	public Education mapEducationRequestToEducation(EducationRequest er) {
		Education e = new Education();
//		e.setId(er.getEducationId());
		e.setFromDate(er.getFromDate());
		e.setToDate(er.getToDate());
		
		Country2 country2 = country2Repo.findById(er.getCountryId())
				.orElseThrow(() -> new ResourceNotFoundException("Country2", "countryId", er.getCountryId()));
		e.setCountry(country2);
		
		University university = universityRepo.findById(er.getUniversityId())
				.orElseThrow(() -> new ResourceNotFoundException("University", "universityId", er.getUniversityId()));
		e.setUniversity(university);
		
		Major major = majorRepo.findById(er.getMajorId())
				.orElseThrow(() -> new ResourceNotFoundException("Major", "majorId", er.getMajorId()));
		e.setMajor(major);
		return e;
	}
	
	public Experience mapExperienceRequestToExperience(ExperienceRequest er) {
		Experience e = new Experience();
//		e.setId(er.getExperienceId());
		e.setCountry(
			countryRepo.findById(er.getCountryId())
				.orElseThrow(() -> new ResourceNotFoundException("Country", "countryId", er.getCountryId()))
		);
		e.setState(
			stateRepo.findById(er.getStateId())
				.orElseThrow(() -> new ResourceNotFoundException("State", "stateId", er.getStateId()))
		);
		e.setCity(
			cityRepo.findById(er.getCityId())
				.orElseThrow(() -> new ResourceNotFoundException("City", "cityId", er.getCityId()))
		);
		e.setOrganization(er.getOrganization());
		e.setPosition(er.getPosition());
		e.setFromDate(er.getFromDate());
		e.setToDate(er.getToDate());
		
		return e;
	}

}
