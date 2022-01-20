package com.demo.springgraphql.mysql.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.springgraphql.mysql.model.Car;

public interface CarRepository extends JpaRepository<Car, Long> {

}
