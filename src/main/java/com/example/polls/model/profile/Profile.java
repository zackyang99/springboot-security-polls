package com.example.polls.model.profile;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.Size;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.GenericGenerator;

import com.example.polls.model.User;
import com.example.polls.validation.CollectionSize;

@Entity
@Table(name="profiles")
public class Profile {
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Long id;
	
	@Id
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
	
	@OneToMany(
			mappedBy="profile",
			fetch=FetchType.EAGER,
			cascade=CascadeType.ALL,
			orphanRemoval=true
	)
	@Fetch(FetchMode.SELECT)
	@BatchSize(size = 30)
	@Size(min=0, max=5)
	private List<Education> educations = new ArrayList<Education>();
	
	@OneToMany(
			mappedBy="profile",
			fetch=FetchType.EAGER,
			cascade=CascadeType.ALL,
			orphanRemoval=true
	)
	@Size(min=0, max=5)
	@Fetch(FetchMode.SELECT)
	@BatchSize(size = 30)
	@Valid
	private List<Experience> experiences = new ArrayList<Experience>();
	
//	@OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_id")
//	User user;
	
//	@OneToOne(fetch = FetchType.LAZY)
//	@MapsId
//	User user;
	
	@OneToOne(fetch = FetchType.LAZY, optional=false)
	@PrimaryKeyJoinColumn
	User user;

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

	public List<Education> getEducations() {
		return educations;
	}

	public void setEducations(List<Education> educations) {
		this.educations = educations;
	}
	
	public void addEducation(Education e) {
		this.educations.add(e);
		e.setProfile(this);
	}
	
	public void removeEducation(Education e) {
		this.educations.remove(e);
		e.setProfile(null);
	}
	
	public void addExperience(Experience e) {
		this.experiences.add(e);
		e.setProfile(this);
	}
	
	public void removeExperience(Experience e) {
		this.experiences.remove(e);
		e.setProfile(null);
	}

	public List<Experience> getExperiences() {
		return experiences;
	}

	public void setExperiences(List<Experience> experiences) {
		this.experiences = experiences;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Profile p = (Profile) o;
		return Objects.equals(id, p.id);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
}
