package com.demo.springgraphql.mysql.resolver;

import java.util.Optional;

import com.demo.springgraphql.mysql.model.Dealer;
import com.demo.springgraphql.mysql.model.Manufacturer;
import com.demo.springgraphql.mysql.model.Owner;
import com.demo.springgraphql.mysql.repository.DealerRepository;
import com.demo.springgraphql.mysql.repository.ManufacturerRepository;
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
	private ManufacturerRepository manufacturerRepository;
	private DealerRepository dealerRepository;

	@Autowired
	public Mutation(OwnerRepository ownerRepository, CarRepository carRepository,
					ManufacturerRepository manufacturerRepository, DealerRepository dealerRepository ) {

		this.ownerRepository = ownerRepository;
		this.carRepository = carRepository;
		this.manufacturerRepository = manufacturerRepository;
		this.dealerRepository = dealerRepository;

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

	public Manufacturer createManufacturer(String mName, Integer vin) {
		Manufacturer manufacturer = new Manufacturer();
		manufacturer.setmName(mName);
		manufacturer.setVin(vin);

		manufacturerRepository.save(manufacturer);

		return manufacturer;
	}

	public Dealer createDealer(Dealer dealer) {
		Manufacturer manufacturer1;

		if( manufacturerRepository.findBymName(dealer.getManufacturer().getmName()) != null){

			manufacturer1 = manufacturerRepository.findBymName(dealer.getManufacturer().getmName());

		}else {

			manufacturer1 = new Manufacturer();
			manufacturer1.setmName(dealer.getManufacturer().getmName());
			manufacturer1.setVin(dealer.getManufacturer().getVin());
			manufacturerRepository.save(manufacturer1);

		}

		Dealer dealer1 = new Dealer(dealer.getdName(), dealer.getEmail(), manufacturer1);

		dealerRepository.save(dealer1);

		return dealer1;
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

	public Owner updateOwner(Long id, String name, Integer age) throws NotFoundException {
		Optional<Owner> optOwner = ownerRepository.findById(id);

		if (optOwner.isPresent()) {
			Owner owner = optOwner.get();

			if (name != null)
				owner.setName(name);
			if (age != null)
				owner.setAge( age);

			ownerRepository.save(owner);
			return owner;
		}

		throw new NotFoundException("Not found owner to update!");
	}

	public Dealer updateDealer(Long id, String dName, String email) throws NotFoundException {
		Optional<Dealer> optDealer = dealerRepository.findById(id);

		if (optDealer.isPresent()) {
			Dealer dealer = optDealer.get();

			if (dName != null)
				dealer.setdName(dName);
			if (email != null)
				dealer.setEmail(email);

			dealerRepository.save(dealer);
			return dealer;
		}

		throw new NotFoundException("Not found dealer to update!");
	}

	public Manufacturer updateManufacturer(Long id, String name, Integer vin) throws NotFoundException {
		Optional<Manufacturer> optionalManufacturer = manufacturerRepository.findById(id);

		if (optionalManufacturer.isPresent()) {
			Manufacturer manufacturer = optionalManufacturer.get();

			if (name != null)
				manufacturer.setmName(name);
			if (vin != null)
				manufacturer.setVin( vin);

			manufacturerRepository.save(manufacturer);
			return manufacturer;
		}

		throw new NotFoundException("Not found Manufacturer to update!");
	}

	public boolean deleteCar(Long id) {
		carRepository.deleteById(id);
		return true;
	}

	public boolean deleteOwner(Long id) {
		ownerRepository.deleteById(id);
		return true;
	}

	public boolean deleteDealer(Long id) {
		dealerRepository.deleteById(id);
		return true;
	}

	public boolean deleteManufacturer(Long id) {

		manufacturerRepository.deleteById(id);
		return true;
	}
}
