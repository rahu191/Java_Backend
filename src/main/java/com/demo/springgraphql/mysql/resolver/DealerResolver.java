package com.demo.springgraphql.mysql.resolver;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.demo.springgraphql.mysql.model.Dealer;
import com.demo.springgraphql.mysql.model.Manufacturer;
import com.demo.springgraphql.mysql.repository.ManufacturerRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class DealerResolver implements GraphQLResolver<Dealer> {
    @Autowired
    private ManufacturerRepository manufacturerRepository;

    public DealerResolver(ManufacturerRepository manufacturerRepository) {
        this.manufacturerRepository = manufacturerRepository;
    }

    public Manufacturer getManufacturer(Dealer dealer) {
        return manufacturerRepository.findById(dealer.getManufacturer().getId()).orElseThrow(null);
    }
}
