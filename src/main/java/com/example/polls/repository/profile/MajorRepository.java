package com.example.polls.repository.profile;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.polls.model.profile.Major;

public interface MajorRepository extends JpaRepository<Major, Integer> {
	public List<Major> findAll();

}
