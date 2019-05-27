package com.example.polls.repository.profile;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.polls.model.profile.EmailVerification;

public interface EmailVerificationRepository extends JpaRepository<EmailVerification, String> {

}
