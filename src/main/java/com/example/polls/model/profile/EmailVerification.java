package com.example.polls.model.profile;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.example.polls.model.audit.DateAudit;

@Entity
@Table(name="EmailVerification")
public class EmailVerification extends DateAudit {
	@Id
	@NotNull
	private String email;
	
	@NotNull
	private String code;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
