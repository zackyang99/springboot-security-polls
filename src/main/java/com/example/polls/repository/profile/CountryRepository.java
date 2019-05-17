package com.example.polls.repository.profile;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.polls.model.profile.Country;
import com.example.polls.model.profile.State;

public interface CountryRepository extends JpaRepository<Country, Integer>{
	public List<Country> findAll();
	
	@Query("SELECT c.states FROM Country c where c.id = :countryId")
	public List<State> findStatesByCountryId(@Param("countryId") Integer countryId);
}
