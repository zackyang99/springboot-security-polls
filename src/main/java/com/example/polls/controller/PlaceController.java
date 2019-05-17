package com.example.polls.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.polls.payload.profile.PlaceResponse;
import com.example.polls.service.PlaceService;

@RestController
@RequestMapping("/place")
public class PlaceController {
	private Logger logger = LoggerFactory.getLogger(PlaceController.class);
	@Autowired
	PlaceService placeService;
	
	@GetMapping("/countries")
	public List<PlaceResponse> getCountryList() {
		return placeService.findAllCountries();
	}
	
	@GetMapping("/states")
	public List<PlaceResponse> getStateListByCountryId(@RequestParam(name="countryId") Integer countryId) {
		return placeService.findStatesByCountryId(countryId);
	}
	
	@GetMapping("/cities")
	public List<PlaceResponse> getCityList(@RequestParam(name="stateId") Integer stateId) {
		return placeService.findCitiesByStateId(stateId);
	}
	
	@GetMapping("/countries2")
	public List<PlaceResponse> getCountryListForUniversity() {
		return placeService.findAllCountries2();
	}
	
	@GetMapping("/universities")
	public List<PlaceResponse> getUniversityListByCountryId(@RequestParam(name="countryId") Integer countryId) {
		return placeService.findUniversitiesByCountryId(countryId);
	}
}
