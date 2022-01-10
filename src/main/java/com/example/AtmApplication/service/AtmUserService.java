package com.example.AtmApplication.service;

import com.example.AtmApplication.model.AtmAccount;
import com.example.AtmApplication.model.AtmUser;

import java.util.List;

public interface AtmUserService {
    AtmUser saveAtmUser(AtmUser atmUser);
    AtmAccount saveAtmAccount(AtmAccount atmAccount);
    AtmUser getAtmUser(String username);
    List<AtmUser> getAtmUsers();
    AtmAccount findById( Long Account_id);
    AtmAccount addAtmAccountToAtmUser(String username);
    public List<AtmAccount> findAllWithMinimumBalance(Double minimumBalance);
}
