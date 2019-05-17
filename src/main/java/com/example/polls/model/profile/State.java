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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.data.annotation.Immutable;

@Entity
@Table(name="states")
public class State {
	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotBlank
	@Size(max=30)
	private String name;
	
	@ManyToOne(fetch=FetchType.LAZY, optional=false)
	@JoinColumn(name="country_id", nullable=false)
	private Country country;
	
	@OneToMany(
			mappedBy="state",
			fetch=FetchType.LAZY,
			cascade=CascadeType.ALL,
			orphanRemoval=true
	)
	@Fetch(FetchMode.SELECT)
	@BatchSize(size=30)
	private List<City> cities = new ArrayList<City>();

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
	
	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}
	
	public List<City> getCities() {
		return cities;
	}

	public void setCities(List<City> cities) {
		this.cities = cities;
	}
	
	public void addCity(City city) {
		cities.add(city);
		city.setState(this);
	}
	
	public void removeCity(City city) {
		cities.remove(city);
		city.setState(null);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		State state = (State) o;
		return Objects.equals(id,  state.id);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
}
