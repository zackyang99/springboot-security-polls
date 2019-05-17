package com.example.polls.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.polls.model.profile.City;
import com.example.polls.model.profile.Country;
import com.example.polls.model.profile.Country2;
import com.example.polls.model.profile.Major;
import com.example.polls.model.profile.State;
import com.example.polls.model.profile.University;
import com.example.polls.payload.profile.MajorResponse;
import com.example.polls.payload.profile.PlaceResponse;
import com.example.polls.repository.profile.Country2Repository;
import com.example.polls.repository.profile.CountryRepository;
import com.example.polls.repository.profile.MajorRepository;
import com.example.polls.repository.profile.StateRepository;

@Service
public class PlaceService {
	@Autowired
	CountryRepository countryRepo;
	
	@Autowired
	StateRepository stateRepo;
	
	@Autowired
	Country2Repository country2Repo;
	
	@Autowired
	MajorRepository majorRepo;
	
	public List<PlaceResponse> findAllCountries() {
		List<Country> countries = countryRepo.findAll();
		List<PlaceResponse> countryResponse = new ArrayList<PlaceResponse>();
		countries.forEach(country -> {
			PlaceResponse pr = new PlaceResponse();
			pr.setId(country.getId());
			pr.setName(country.getName());
			countryResponse.add(pr);
		});
		
		return countryResponse;
	}
	
	public List<PlaceResponse> findStatesByCountryId(Integer id) {
		List<State> states = countryRepo.findStatesByCountryId(id);
		List<PlaceResponse> stateReponse = new ArrayList<PlaceResponse>();
		
		states.forEach(state -> {
			PlaceResponse pr = new PlaceResponse();
			pr.setId(state.getId());
			pr.setName(state.getName());
			stateReponse.add(pr);
		});
		
		return stateReponse;
	}
	
	public List<PlaceResponse> findCitiesByStateId(Integer id) {
		List<City> cities = stateRepo.findCitiesByStateId(id);
		List<PlaceResponse> stateReponse = new ArrayList<PlaceResponse>();
		
		cities.forEach(city -> {
			PlaceResponse pr = new PlaceResponse();
			pr.setId(city.getId());
			pr.setName(city.getName());
			stateReponse.add(pr);
		});
		
		return stateReponse;
	}
	
	public List<PlaceResponse> findAllCountries2() {
		List<Country2> countries2 = country2Repo.findAll();
		List<PlaceResponse> countries2Response = new ArrayList<PlaceResponse>();
		
		countries2.forEach(c -> {
			PlaceResponse pr = new PlaceResponse();
			pr.setId(c.getId());
			pr.setName(c.getName());
			countries2Response.add(pr);
		});
		
		return countries2Response;
	}
	
	public List<PlaceResponse> findUniversitiesByCountryId(Integer countryId) {
		List<University> universities = country2Repo.findUniversitiesByCountryId(countryId);
		List<PlaceResponse> universityResponse = new ArrayList<PlaceResponse>();
		
		universities.forEach(u -> {
			PlaceResponse pr = new PlaceResponse();
			pr.setId(u.getId());
			pr.setName(u.getName());
			universityResponse.add(pr);
		});
		
		return universityResponse;
	}
	
	public List<MajorResponse> findMajorList() {
		List<Major> majors = majorRepo.findAll();
		List<MajorResponse> majorResponse = new ArrayList<MajorResponse>();
		
		majors.forEach(m -> {
			MajorResponse mr = new MajorResponse();
			mr.setId(m.getId());
			mr.setMajor(m.getMajor());
			majorResponse.add(mr);
		});
		
		return majorResponse;
	}
}
