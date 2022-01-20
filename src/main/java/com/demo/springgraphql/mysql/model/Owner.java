package com.demo.springgraphql.mysql.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

@Entity
public class Owner {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "age")
	private Integer age;

	@OneToMany( fetch = FetchType.EAGER, mappedBy = "owner")
	@JsonManagedReference
	private List<Car> cars ;

	public Owner() {
	}

	public Owner(Long id) {
		this.id = id;
	}

	public Owner(String name, Integer age,List <Car> cars) {
		this.name = name;
		this.age = age;
		this.cars = cars;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public List<Car> getCars() {
		return cars;
	}

	public void setCars(List<Car> cars) {
		this.cars = cars;
	}

	@Override
	public String toString() {
		return "Author{" +
				"id=" + id +
				", name='" + name + '\'' +
				", age=" + age +
				", cars=" + cars +
				'}';
	}
}
