package com.demo.springgraphql.mysql.resolver;

import com.demo.springgraphql.mysql.model.Owner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.demo.springgraphql.mysql.model.Car;
import com.demo.springgraphql.mysql.repository.OwnerRepository;
import com.coxautodev.graphql.tools.GraphQLResolver;

import java.util.List;

@Component
public class CarResolver implements GraphQLResolver<Car> {
	@Autowired
	private OwnerRepository ownerRepository;

	public CarResolver(OwnerRepository ownerRepository) {
		this.ownerRepository = ownerRepository;
	}

	public Owner getOwner(Car car) {
		return ownerRepository.findById(car.getOwner().getId()).orElseThrow(null);
	}
}
