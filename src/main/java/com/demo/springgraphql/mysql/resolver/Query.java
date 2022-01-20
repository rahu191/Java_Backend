package com.demo.springgraphql.mysql.resolver;

import com.demo.springgraphql.mysql.model.Car;
import com.demo.springgraphql.mysql.model.Dealer;
import com.demo.springgraphql.mysql.model.Manufacturer;
import com.demo.springgraphql.mysql.model.Owner;
import com.demo.springgraphql.mysql.repository.DealerRepository;
import com.demo.springgraphql.mysql.repository.ManufacturerRepository;
import com.demo.springgraphql.mysql.repository.OwnerRepository;
import com.demo.springgraphql.mysql.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;

@Component
public class Query implements GraphQLQueryResolver {
	private OwnerRepository ownerRepository;
	private CarRepository carRepository;
	private DealerRepository dealerRepository;
	private ManufacturerRepository manufacturerRepository;

	@Autowired
	public Query(OwnerRepository ownerRepository, CarRepository carRepository, DealerRepository dealerRepository, ManufacturerRepository manufacturerRepository) {
		this.ownerRepository = ownerRepository;
		this.carRepository = carRepository;
		this.dealerRepository = dealerRepository;
		this.manufacturerRepository = manufacturerRepository;
	}

	public Iterable<Owner> findAllOwners() {
		return ownerRepository.findAll();
	}

	public Iterable<Car> findAllCars() {
		return carRepository.findAll();
	}

	public Iterable<Dealer> findAllDealers() { return dealerRepository.findAll(); }

	public Iterable<Manufacturer> findAllManufacturers() { return manufacturerRepository.findAll(); }

	public long countOwners() {
		return ownerRepository.count();
	}

	public long countCars() {
		return carRepository.count();
	}

	public long countManufacturers() { return manufacturerRepository.count(); }

	public long countDealers() { return dealerRepository.count(); }

}
