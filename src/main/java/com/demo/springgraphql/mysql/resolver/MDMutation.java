package com.demo.springgraphql.mysql.resolver;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.demo.springgraphql.mysql.model.Car;
import com.demo.springgraphql.mysql.model.Dealer;
import com.demo.springgraphql.mysql.model.Manufacturer;
import com.demo.springgraphql.mysql.model.Owner;
import com.demo.springgraphql.mysql.repository.DealerRepository;
import com.demo.springgraphql.mysql.repository.ManufacturerRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class MDMutation implements GraphQLMutationResolver {
    private ManufacturerRepository manufacturerRepository;
    private DealerRepository dealerRepository;

    @Autowired
    public MDMutation(ManufacturerRepository manufacturerRepository, DealerRepository dealerRepository) {
        this.manufacturerRepository = manufacturerRepository;
        this.dealerRepository = dealerRepository;
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

    public boolean deleteDealer(Long id) {
        dealerRepository.deleteById(id);
        return true;
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

        throw new NotFoundException("Not found Car to update!");
    }
}