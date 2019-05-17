package com.example.polls.repository.profile;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.polls.model.profile.City;
import com.example.polls.model.profile.State;

public interface StateRepository extends JpaRepository<State, Integer> {
	@Query("SELECT s.cities FROM State s where s.id = :stateId")
	public List<City> findCitiesByStateId(@Param("stateId") Integer stateId);
}
