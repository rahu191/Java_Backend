package com.example.bankingassignment.controller;

import com.example.bankingassignment.dto.JwtAuthenticationRequest;
import com.example.bankingassignment.dto.JwtAuthenticationResponse;
import com.example.bankingassignment.dto.UserDto;
import com.example.bankingassignment.models.Account;
import com.example.bankingassignment.models.User;
import com.example.bankingassignment.services.AccountService;
import com.example.bankingassignment.services.MyUserDetailsService;
import com.example.bankingassignment.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JwtAuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private JwtUtil jwtTokenUtil;
    @Autowired
    private PasswordEncoder bcryptEncoder;


    @RequestMapping("/hello")
    public String hello() {
        return ("<h1>hello</h1>");
    }

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtAuthenticationRequest authenticationRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password");
        }

        final UserDetails userDetails = myUserDetailsService.loadUserByUsername(authenticationRequest.getUsername());

        final String jwt = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));

    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<?> saveUser(@RequestBody UserDto userDto) throws Exception {
        User user = myUserDetailsService.saveUser(new User(userDto.getFirstname(), userDto.getLastname(), userDto.getEmailId(), userDto.getUsername(), bcryptEncoder.encode(userDto.getPassword())));
        Account account = accountService.createAccount(new Account());
        user.setAccount(account);
        account.setAccountOwner(user);
        return ResponseEntity.ok(myUserDetailsService.saveUser(user));
    }
}
