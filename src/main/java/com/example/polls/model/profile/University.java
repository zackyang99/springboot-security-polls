package com.example.polls.model.profile;

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
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Immutable;

@Entity
@Table(name="universities")
@Immutable
public class University {
	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotBlank
	@Size(max=150)
	private String name;
	
	@NotBlank
	@Size(max=150)
	private String url;
	
	@ManyToOne(fetch=FetchType.LAZY, optional=false)
	@JoinColumn(name="country_id", nullable=false)
	private Country2 country;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Country2 getCountry() {
		return country;
	}

	public void setCountry(Country2 country) {
		this.country = country;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		University u = (University) o;
		return Objects.equals(id,  u.id);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
}
