package com.example.polls.payload.profile;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Size;

import com.example.polls.validation.CollectionSize;

public class ProfileRequest {
	long profileId;
	int countryId;
	int stateId;
	int cityId;
	
	@CollectionSize(min=0, max=3)
	List<EducationRequest> educationRequest = new ArrayList<EducationRequest>();
	@CollectionSize(min=0, max=5)
	List<ExperienceRequest> experienceRequest = new ArrayList<ExperienceRequest>();
	public long getProfileId() {
		return profileId;
	}
	public void setProfileId(long profileId) {
		this.profileId = profileId;
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
	public List<EducationRequest> getEducationRequest() {
		return educationRequest;
	}
	public void setEducationRequest(List<EducationRequest> educationRequest) {
		this.educationRequest = educationRequest;
	}
	public List<ExperienceRequest> getExperienceRequest() {
		return experienceRequest;
	}
	public void setExperienceRequest(List<ExperienceRequest> experienceRequest) {
		this.experienceRequest = experienceRequest;
	}
}
