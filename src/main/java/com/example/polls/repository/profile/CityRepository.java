package com.example.polls.repository.profile;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.polls.model.profile.City;

public interface CityRepository extends JpaRepository<City, Integer> {

}
