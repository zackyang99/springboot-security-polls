package com.example.polls.payload.profile;

import java.time.Instant;

public class EducationRequest {
	private long educationId;
	private int countryId;
	private int universityId;
	private int majorId;
	private Instant fromDate;
	private Instant toDate;
	public long getEducationId() {
		return educationId;
	}
	public void setEducationId(long educationId) {
		this.educationId = educationId;
	}
	public int getCountryId() {
		return countryId;
	}
	public void setCountryId(int countryId) {
		this.countryId = countryId;
	}
	public int getUniversityId() {
		return universityId;
	}
	public void setUniversityId(int universityId) {
		this.universityId = universityId;
	}
	public int getMajorId() {
		return majorId;
	}
	public void setMajorId(int majorId) {
		this.majorId = majorId;
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
