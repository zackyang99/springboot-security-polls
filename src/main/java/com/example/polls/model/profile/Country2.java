package com.example.polls.model.profile;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name="countries2")
public class Country2 {
	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotBlank
	@Size(max=2)
	private String iso2;
	
	@NotBlank
	@Size(max=3)
	private String iso3;
	
	@NotBlank
	@Size(max=44)
	private String name;
	
	@OneToMany(
			mappedBy="country",
			fetch=FetchType.LAZY,
			cascade=CascadeType.ALL,
			orphanRemoval=true
	)
	@Fetch(FetchMode.SELECT)
	@BatchSize(size=30)
	private List<University> universities = new ArrayList<University>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIso2() {
		return iso2;
	}

	public void setIso2(String iso2) {
		this.iso2 = iso2;
	}

	public String getIso3() {
		return iso3;
	}

	public void setIso3(String iso3) {
		this.iso3 = iso3;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<University> getUniversities() {
		return universities;
	}

	public void setUniversities(List<University> universities) {
		this.universities = universities;
	}
	
	public void addUniversity(University university) {
		this.universities.add(university);
		university.setCountry(this);
	}

	public void removeUniversity(University university) {
		this.universities.remove(university);
		university.setCountry(null);
	}
}
