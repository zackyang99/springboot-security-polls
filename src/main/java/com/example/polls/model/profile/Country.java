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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.data.annotation.Immutable;

@Entity
@Table(name="countries")
public class Country {
	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotBlank
	@Size(max=2)
	private String shortname;
	
	@NotBlank
	@Size(max=36)
	private String name;
	
	@NotBlank
	@Size(max=4)
	private String phonecode;
	
	@OneToMany(
			mappedBy="country",
			fetch=FetchType.LAZY,
			cascade=CascadeType.ALL,
			orphanRemoval=true
	)
	@Fetch(FetchMode.SELECT)
	@BatchSize(size=30)
	private List<State> states = new ArrayList<State>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getShortname() {
		return shortname;
	}

	public void setShortname(String shortname) {
		this.shortname = shortname;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhonecode() {
		return phonecode;
	}

	public void setPhonecode(String phonecode) {
		this.phonecode = phonecode;
	}

	public List<State> getStates() {
		return states;
	}

	public void setStates(List<State> states) {
		this.states = states;
	}

	public void addState(State state) {
		states.add(state);
		state.setCountry(this);
	}
	
	public void removeState(State state) {
		states.remove(state);
		state.setCountry(null);
	}
}
