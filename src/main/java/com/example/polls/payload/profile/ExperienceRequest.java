package com.example.polls.payload.profile;

import java.time.Instant;

public class ExperienceRequest {
	private long experienceId;
	private int countryId;
	private int stateId;
	private int cityId;
	private String organization;
	private String position;
	private Instant fromDate;
	private Instant toDate;
	public long getExperienceId() {
		return experienceId;
	}
	public void setExperienceId(long experienceId) {
		this.experienceId = experienceId;
	}
	public int getCountryId() {
		return countryId;
	}
	public void setCountryId(int countryId) {
		this.countryId = countryId;
	}
	public int getStateId() {
		return stateId;
	}
	public void setStateId(int stateId) {
		this.stateId = stateId;
	}
	public int getCityId() {
		return cityId;
	}
	public void setCityId(int cityId) {
		this.cityId = cityId;
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
}
