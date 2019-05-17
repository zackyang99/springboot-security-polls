package com.example.polls.model.profile;

import java.time.Instant;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="educations")
public class Education {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch=FetchType.LAZY, optional=false)
	@JoinColumn(name="country_id", nullable=false)
	private Country2 country;
	
	@ManyToOne(fetch=FetchType.LAZY, optional=false)
	@JoinColumn(name="university_id", nullable=false)
	private University university;
	
	@ManyToOne(fetch=FetchType.LAZY, optional=false)
	@JoinColumn(name="major_id", nullable=false)
	private Major major;
	
	@NotNull
	private Instant fromDate;
	
	@NotNull
	private Instant toDate;
	
	@ManyToOne(fetch=FetchType.LAZY, optional=false)
	@JoinColumn(name="profile_id", nullable=false)
	private Profile profile;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Country2 getCountry() {
		return country;
	}

	public void setCountry(Country2 country) {
		this.country = country;
	}

	public University getUniversity() {
		return university;
	}

	public void setUniversity(University university) {
		this.university = university;
	}

	public Major getMajor() {
		return major;
	}

	public void setMajor(Major major) {
		this.major = major;
	}
	
	public Instant getFromDate() {
		return fromDate;
	}

	public void setFromDate(Instant fromDate) {
		this.fromDate = fromDate;
	}

	public Instant getToDate() {
		return toDate;
	}

	public void setToDate(Instant toDate) {
		this.toDate = toDate;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Education education = (Education) o;
		return Objects.equals(id,  education.id);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
}
