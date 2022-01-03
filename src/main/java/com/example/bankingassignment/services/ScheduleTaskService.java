package com.example.bankingassignment.services;


import com.example.bankingassignment.models.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ScheduleTaskService {

    @Autowired
    private AccountService accountService;

    @Autowired
    private UserService userService;
    @Autowired
    private MailService mailService;

    @Scheduled(cron = "*/10 * * * * *")
    public void checkMinimumBalance() {

        Double minimumBalance = 2000.0;

        List<Account> lowBalanceAccounts = accountService.findAllWithMinimumBalance(minimumBalance);

        lowBalanceAccounts.stream().forEach(account -> {
            try {
                System.out.println(account.getAccountOwner().getEmailId());
                mailService.sendEmail(account.getAccountOwner().getEmailId(),
                        "Low Account Balance",
                        "Your Account balance is Low: " + account.getBalance());
            } catch (Exception e) {
                System.out.println("DEPOSIT CONTROLLER :: " + e);
            }
        });

    }

}
