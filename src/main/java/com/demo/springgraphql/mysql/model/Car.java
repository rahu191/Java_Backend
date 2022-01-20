package com.demo.springgraphql.mysql.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
public class Car {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "carName", nullable = false)
	private String carName;

	@Column(name = "description")
	private String description;

	@ManyToOne( fetch = FetchType.EAGER)
	@JsonBackReference
	@JoinColumn(name = "owner_id", nullable = false, updatable = false)
	private Owner owner;

	public Car() {
	}

	public Car(String carName, String description, Owner owner) {
		this.carName = carName;
		this.description = description;
		this.owner = owner;
	}

	public Long getId() {
		return id;
	}

	public String getcarName() {
		return carName;
	}

	public void setcarName(String carName) {
		this.carName = carName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Owner getOwner() {
		return owner;
	}

	public void setOwner(Owner owner) {
		this.owner = owner;
	}

	@Override
	public String toString() {
		return "Tutorial [id=" + id + ", carName=" + carName + ", description=" + description + ", owner=" + owner + "]";
	}

}
