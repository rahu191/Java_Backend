package com.example.bankingassignment.repo;

import com.example.bankingassignment.models.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    private final User user = new User(
            "XYZ", "ABC", "test@test.com", "xyzabc", "123"
    );

    @Test
    void findByUsername() {



    }
}