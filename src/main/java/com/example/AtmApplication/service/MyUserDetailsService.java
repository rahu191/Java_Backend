package com.example.AtmApplication.service;

import com.example.AtmApplication.model.AtmUser;
import com.example.AtmApplication.repository.AtmUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private AtmUserRepository atmUserRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        AtmUser atmUser = atmUserRepository.findByUsername(userName);
        return new org.springframework.security.core.userdetails.User(atmUser.getUsername(), atmUser.getPassword(), new ArrayList<>());
    }
}
