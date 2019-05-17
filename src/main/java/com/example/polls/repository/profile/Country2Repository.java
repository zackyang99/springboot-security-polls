package com.example.polls.repository.profile;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.polls.model.profile.Country2;
import com.example.polls.model.profile.University;

public interface Country2Repository extends JpaRepository<Country2, Integer> {
	public List<Country2> findAll();
	
	@Query("SELECT c.universities FROM Country2 c WHERE c.id = :countryId")
	public List<University> findUniversitiesByCountryId(@Param("countryId") Integer countryId);

}
