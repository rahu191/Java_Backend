package com.demo.springgraphql.mysql.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
public class Dealer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "dName", nullable = false)
    private String dName;

    @Column(name = "email")
    private String email;

    @ManyToOne( fetch = FetchType.EAGER)
    @JsonBackReference
    @JoinColumn(name = "manufacturer_id", nullable = false, updatable = false)
    private Manufacturer manufacturer;

    public Dealer() {
    }

    public Dealer(String dName, String email, Manufacturer manufacturer) {
        this.dName = dName;
        this.email = email;
        this.manufacturer = manufacturer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getdName() {
        return dName;
    }

    public void setdName(String dName) {
        this.dName = dName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    @Override
    public String toString() {
        return "Dealer{" +
                "id=" + id +
                ", dName='" + dName + '\'' +
                ", email='" + email + '\'' +
                ", manufacturer=" + manufacturer +
                '}';
    }
}

