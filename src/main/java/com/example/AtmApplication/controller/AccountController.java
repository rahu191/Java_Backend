package com.example.AtmApplication.controller;

import com.example.AtmApplication.AtmDto.Account_idToAmount;
import com.example.AtmApplication.model.AtmAccount;
import com.example.AtmApplication.service.AtmUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AccountController {

    @Autowired
    private AtmUserService atmUserService;

    @PutMapping("/users/deposit/{Account_id}")
    public ResponseEntity<AtmAccount> depositBalance(@PathVariable Long Account_id, @RequestBody Account_idToAmount form) {
        AtmAccount atmAccount = atmUserService.findById(Account_id);
        atmAccount.setBalance(atmAccount.getBalance() + form.getAmount());
        atmUserService.saveAtmAccount(atmAccount);

        return ResponseEntity.ok().body(atmUserService.findById(Account_id));
    }

    @GetMapping("/users/view")
    public ResponseEntity<AtmAccount> viewBalance(@RequestBody Long account_id) {

        return ResponseEntity.ok().body(atmUserService.findById(account_id));
    }

    @PutMapping("/users/withdraw/{Account_id}")
    public ResponseEntity<String> withdrawBalance(@PathVariable Long Account_id, @RequestBody Account_idToAmount form) {
        AtmAccount atmAccount = atmUserService.findById(Account_id);
            if( atmAccount.getBalance() - form.getAmount() > 2000) {
                atmAccount.setBalance(atmAccount.getBalance() - form.getAmount());
                atmUserService.saveAtmAccount(atmAccount);
            }else{
                return  new ResponseEntity<>("Insufficient Balance", HttpStatus.OK);
        }
        return new ResponseEntity<>("WithDrawn Successfully!! Current Balance is" + atmAccount.getBalance()
                                        , HttpStatus.OK);
    }



}
