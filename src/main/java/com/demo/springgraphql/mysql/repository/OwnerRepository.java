package com.demo.springgraphql.mysql.repository;

import com.demo.springgraphql.mysql.model.Car;
import com.demo.springgraphql.mysql.model.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OwnerRepository extends JpaRepository<Owner, Long> {
}