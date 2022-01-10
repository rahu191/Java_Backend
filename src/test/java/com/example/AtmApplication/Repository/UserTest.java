package com.example.AtmApplication.Repository;

import com.example.AtmApplication.model.AtmUser;
import com.example.AtmApplication.repository.AtmUserRepository;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class UserTest {
    @Autowired
    private AtmUserRepository atmUserRepository;

    @Test
    @Order(1)
    public void testUserCreate() {
        AtmUser atmUser = new AtmUser();
        atmUser.setEmail("testUser@gmail.com");
        atmUser.setUsername("Testing Johny");
        atmUser.setPassword("Testing test");
        atmUserRepository.save(atmUser);
        assertNotNull(atmUserRepository.findByUsername("Testing Johny"));
    }

    @Test
    @Order(2)
    public void testSingleUser() {

        assertNotNull("Testing Johny",atmUserRepository.findByEmail("testUser@gmail.com").getUsername());
    }

    @Test
    @Order(3)
    public void testUserUpdate(){
        AtmUser atmUser = atmUserRepository.findByUsername("Testing Johny");
        atmUser.setEmail("testUserUpdate@gmail.com");
        atmUserRepository.save(atmUser);
        assertNotEquals("testUser@gmail.com", atmUserRepository.findByUsername("Testing Johny").getEmail());
    }


}
