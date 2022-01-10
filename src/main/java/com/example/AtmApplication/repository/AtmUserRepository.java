package com.example.AtmApplication.repository;

import com.example.AtmApplication.model.AtmUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface AtmUserRepository extends JpaRepository<AtmUser, Long> {

    AtmUser findByUsername(String username);
    AtmUser findByEmail(String email);
}
