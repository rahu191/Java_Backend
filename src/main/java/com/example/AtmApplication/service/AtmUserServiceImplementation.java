package com.example.AtmApplication.service;

import com.example.AtmApplication.model.AtmAccount;
import com.example.AtmApplication.model.AtmUser;
import com.example.AtmApplication.repository.AtmAccountRepository;
import com.example.AtmApplication.repository.AtmUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service @RequiredArgsConstructor @Transactional
public class AtmUserServiceImplementation implements AtmUserService {

    @Autowired
    private AtmAccountRepository atmAccountRepository;
    @Autowired
    private AtmUserRepository atmUserRepository;

    @Override
    public AtmUser saveAtmUser(AtmUser atmUser) {

        return atmUserRepository.save( atmUser);
    }

    @Override
    public AtmAccount saveAtmAccount(AtmAccount atmAccount) {

        return atmAccountRepository.save( atmAccount);
    }

    @Override
    public AtmUser getAtmUser(String username) {

        return atmUserRepository.findByUsername(username);
    }

    @Override
    public List<AtmUser> getAtmUsers() {

        return atmUserRepository.findAll();
    }

    @Override
    public AtmAccount findById(Long Account_id) {
        System.out.println(Account_id);
        Optional<AtmAccount> atmAccountResponse = atmAccountRepository.findById( Account_id);

        return atmAccountResponse.get();
    }


    @Override
    public AtmAccount addAtmAccountToAtmUser(String username) {
        AtmUser atmUser = atmUserRepository.findByUsername( username);
        AtmAccount atmAccount = new AtmAccount(2000, atmUser);
        atmUserRepository.save(atmUser);
        atmAccountRepository.save(atmAccount);

        return atmAccount;
    }

    @Override
    public List<AtmAccount> findAllWithMinimumBalance(Double minimumBalance) {
        System.out.println(atmAccountRepository.findByBalanceLessThan(minimumBalance));

        return atmAccountRepository.findByBalanceLessThan(minimumBalance);
    }

}
