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
@Table(name="cities")
public class City {
	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotBlank
	@Size(max=32)
	private String name;
	
	@ManyToOne(fetch=FetchType.LAZY, optional=false)
	@JoinColumn(name="state_id", nullable=false)
	private State state;

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

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		City city = (City) o;
		return Objects.equals(id,  city.id);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
}
