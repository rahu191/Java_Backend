package com.example.AtmApplication.Repository;

import com.example.AtmApplication.model.AtmAccount;
import com.example.AtmApplication.repository.AtmAccountRepository;
import com.example.AtmApplication.repository.AtmUserRepository;
import com.example.AtmApplication.service.AtmUserService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AccountTest {

    @Autowired
    private AtmAccountRepository atmAccountRepository;
    @Autowired
    private AtmUserRepository atmUserRepository;
    @Autowired
    private AtmUserService atmUserService;

    @Test
    @Order(1)
    public void AccountTestViewBalance() {
        AtmAccount atmAccount = atmAccountRepository.findByatmUser1(atmUserRepository.findById(2L).get());
        assertEquals(2000.0, atmAccount.getBalance());
    }

    @Test
    @Order(2)
    public void AccountWithdrawBalance() {
        AtmAccount atmAccount = atmAccountRepository.findByatmUser1(atmUserRepository.findById(2L).get());
        if( atmAccount.getBalance() - 1000 > 0) {
            atmAccount.setBalance(atmAccount.getBalance() - 1000);
            atmUserService.saveAtmAccount(atmAccount);
        }else {
            try {
                throw new Exception("Account Balance low");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        assertEquals(1000.0, atmAccount.getBalance());
    }

    @Test
    @Order(3)
    public void AccountDepositBalance() {
        AtmAccount atmAccount = atmAccountRepository.findByatmUser1(atmUserRepository.findById(2L).get());
            atmAccount.setBalance(atmAccount.getBalance() + 1000);
            atmUserService.saveAtmAccount(atmAccount);

        assertEquals(2000.0, atmAccount.getBalance());
    }

}