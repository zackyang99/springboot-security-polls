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
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.example.polls.validation.CollectionSize;

@Entity
@Table(name="experiences")
public class Experience {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch=FetchType.LAZY, optional=false)
	@JoinColumn(name="country_id", nullable=false)
	private Country country;
	
	@ManyToOne(fetch=FetchType.LAZY, optional=false)
	@JoinColumn(name="state_id", nullable=false)
	private State state;
	
	@ManyToOne(fetch=FetchType.LAZY, optional=false)
	@JoinColumn(name="city_id", nullable=false)
	private City city;
	
	@Size(max=100)
	@NotBlank
	private String organization;
	
	@Size(max=100)
	@NotBlank
	private String position;
	
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

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
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
		Experience education = (Experience) o;
		return Objects.equals(id,  education.id);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
}
