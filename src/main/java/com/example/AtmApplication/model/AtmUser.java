package com.example.AtmApplication.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
    @Table(name = "AtmUser")
    @NoArgsConstructor
    @AllArgsConstructor
    public class AtmUser {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private long id;
        private String email;
        private String username;
        private String password;

    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER, mappedBy = "atmUser1")
    @JsonManagedReference
        private List<AtmAccount> atmAccounts ;

    public AtmUser( String email, String username, String password, List<AtmAccount> list) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.atmAccounts = list;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<AtmAccount> getAtmAccounts() {
            return this.atmAccounts;
    }
}
