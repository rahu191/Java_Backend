package com.example.AtmApplication.controller;

import com.example.AtmApplication.AtmDto.AccountToUser;
import com.example.AtmApplication.model.AtmAccount;
import com.example.AtmApplication.model.AtmUser;
import com.example.AtmApplication.models.AuthenticationRequest;
import com.example.AtmApplication.models.AuthenticationResponse;
import com.example.AtmApplication.service.AtmUserService;
import com.example.AtmApplication.service.MyUserDetailsService;
import com.example.AtmApplication.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private AtmUserService atmUserService;

    @Autowired
    private MyUserDetailsService userDetailsService;
    @Autowired
    private JwtUtil jwtTokenUtil;

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticateToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        try {
            System.out.println(authenticationRequest.getUsername()+ "!!!!!!!!!!!1");
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
            );
        } catch(BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }
        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());

        final String jwt = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

    @PostMapping("/saveUser")
    public ResponseEntity<AtmUser> saveAtmUser(@RequestBody AtmUser atmUser) {

        return ResponseEntity.ok().body(atmUserService.saveAtmUser(atmUser));
    }

    @GetMapping("/")
    public ResponseEntity<List<AtmUser>> getAtmUsers() {

        return ResponseEntity.ok().body(atmUserService.getAtmUsers());
    }

    @GetMapping("/{username}")
    public ResponseEntity<AtmUser> getAtmUser(@PathVariable String username ) {

        return ResponseEntity.ok().body(atmUserService.getAtmUser(username));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AtmAccount> findById(@PathVariable Long id) {

      return ResponseEntity.ok().body(atmUserService.findById( id));
    }

    @PostMapping("/newAccount")
    public ResponseEntity<AtmAccount> saveAtmAccount() {

        return ResponseEntity.ok().body(atmUserService.saveAtmAccount(new AtmAccount()));
    }

    @PostMapping("/addAccount")
    public ResponseEntity<AtmAccount> addAtmAccountToAtmUser(@RequestBody AccountToUser accountToUser) {

        return ResponseEntity.ok().body(atmUserService.addAtmAccountToAtmUser(accountToUser.getUsername()));
    }

}
