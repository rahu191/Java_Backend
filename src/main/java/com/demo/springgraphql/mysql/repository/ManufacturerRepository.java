package com.demo.springgraphql.mysql.repository;

import com.demo.springgraphql.mysql.model.Manufacturer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManufacturerRepository extends JpaRepository<Manufacturer, Long> {
    Manufacturer findBymName(String getmName);
}
