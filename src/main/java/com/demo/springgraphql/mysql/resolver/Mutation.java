package com.demo.springgraphql.mysql.resolver;

import java.util.Optional;

import com.demo.springgraphql.mysql.model.Owner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.demo.springgraphql.mysql.model.Car;
import com.demo.springgraphql.mysql.repository.OwnerRepository;
import com.demo.springgraphql.mysql.repository.CarRepository;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;

import javassist.NotFoundException;

@Component
public class Mutation implements GraphQLMutationResolver {
	private OwnerRepository ownerRepository;
	private CarRepository carRepository;

	@Autowired
	public Mutation(OwnerRepository ownerRepository, CarRepository carRepository) {
		this.ownerRepository = ownerRepository;
		this.carRepository = carRepository;
	}

	public Owner createOwner(String name, Integer age) {
		Owner owner = new Owner();
		owner.setName(name);
		owner.setAge(age);

		ownerRepository.save(owner);

		return owner;
	}

	public Car createCar(String carName, String description, Long ownerId) {
		Car car = new Car();
		car.setOwner(new Owner(ownerId));
		car.setcarName(carName);
		car.setDescription(description);

		carRepository.save(car);

		return car;
	}

	public boolean deleteCar(Long id) {
		carRepository.deleteById(id);
		return true;
	}

	public Car updateCar(Long id, String carName, String description) throws NotFoundException {
		Optional<Car> optCar = carRepository.findById(id);

		if (optCar.isPresent()) {
			Car car = optCar.get();

			if (carName != null)
				car.setcarName(carName);
			if (description != null)
				car.setDescription(description);

			carRepository.save(car);
			return car;
		}

		throw new NotFoundException("Not found Car to update!");
	}
}
