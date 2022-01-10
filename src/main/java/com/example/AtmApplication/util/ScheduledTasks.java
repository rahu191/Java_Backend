package com.example.AtmApplication.util;

import com.example.AtmApplication.model.AtmAccount;
import com.example.AtmApplication.model.AtmUser;
import com.example.AtmApplication.model.Mail;
import com.example.AtmApplication.service.AtmUserService;
import com.example.AtmApplication.service.mail.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ScheduledTasks {

    @Autowired
    private AtmUserService atmUserService;
    @Autowired
    private MailService mailService;

    @Scheduled(fixedRate = 30000)
    public void checkMinimumBalance() {
        Double minimumAccountBalance = 3000.0;

        List<AtmAccount> minimumBalanceAccounts = atmUserService.findAllWithMinimumBalance(minimumAccountBalance);

        minimumBalanceAccounts
                .stream()
                .forEach(
                        account -> {
                            AtmUser accountOwner = account.getAtmUser1();
                            String mailBody = "Dear"+accountOwner.getUsername() +"your account with account id"+
                                    account.getAccount_id()+"has low balance of "+
                                    minimumAccountBalance;
                            Mail mail = new Mail();
                            mail.setMailFrom(accountOwner.getEmail());
                            mail.setMailTo(accountOwner.getEmail());
                            mail.setMailSubject("Balance low!!");
                            mail.setMailContent(mailBody);

                            try {
                                mailService.sendEmail(mail);
                            } catch (Exception e) {
                                System.err.println(
                                        "Alert mail not sent, Error: " + e.getLocalizedMessage()
                                );
                            }
                        }
                );
    }
}