package com.example.polls.repository.profile;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.polls.model.profile.Profile;

public interface ProfileRepository extends JpaRepository<Profile, Long> {

}
