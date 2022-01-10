package com.example.AtmApplication.repository;

import com.example.AtmApplication.model.AtmAccount;
import com.example.AtmApplication.model.AtmUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AtmAccountRepository extends JpaRepository<AtmAccount, Long> {
    List<AtmAccount> findByBalanceLessThan(Double minimumBalance);

    AtmAccount findByatmUser1(AtmUser atmUser);
}
