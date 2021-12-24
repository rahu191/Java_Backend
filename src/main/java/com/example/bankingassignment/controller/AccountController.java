package com.example.bankingassignment.controller;

import com.example.bankingassignment.dto.AccountDto;
import com.example.bankingassignment.dto.UserDto;
import com.example.bankingassignment.models.User;
import com.example.bankingassignment.repo.UserRepository;
import com.example.bankingassignment.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/balance", method = RequestMethod.GET)
    public ResponseEntity<?> getBalance() {

        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.findByUsername(username);

        return ResponseEntity.ok().body(user.getAccount().getBalance());
    }

    @RequestMapping(value = "/withdraw", method = RequestMethod.PUT)
    public ResponseEntity<?> withdrawBalance(@RequestBody AccountDto accountDto) {

        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.findByUsername(username);

        user.getAccount().setBalance(user.getAccount().getBalance() - accountDto.getAmount());

        return ResponseEntity.ok(userService.saveUser(user));
    }

    @RequestMapping(value = "/deposit", method = RequestMethod.PUT)
    public ResponseEntity<?> depositBalance(@RequestBody AccountDto accountDto) {
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.findByUsername(username);

        user.getAccount().setBalance(user.getAccount().getBalance() + accountDto.getAmount());

        return ResponseEntity.ok(userService.saveUser(user));
    }


}
