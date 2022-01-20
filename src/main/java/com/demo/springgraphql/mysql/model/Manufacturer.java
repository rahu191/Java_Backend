package com.demo.springgraphql.mysql.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

@Entity
public class Manufacturer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "mName", nullable = false, unique = true)
    private String mName;

    @Column(name = "vin", unique = true)
    private Integer vin;

    @OneToMany( fetch = FetchType.EAGER, mappedBy = "manufacturer")
    @JsonManagedReference
    private List<Dealer> dealers ;

    public Manufacturer() {
    }

    public Manufacturer(Long id) {
        this.id = id;
    }

    public Manufacturer(String mName, Integer vin,List <Dealer> dealers) {
        this.mName = mName;
        this.vin = vin;
        this.dealers = dealers;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public Integer getVin() {
        return vin;
    }

    public void setVin(Integer vin) {
        this.vin = vin;
    }

    public List<Dealer> getDealers() {
        return dealers;
    }

    public void setDealers(List<Dealer> dealers) {
        this.dealers = dealers;
    }

    @Override
    public String toString() {
        return "Manufacturer{" +
                "id=" + id +
                ", mName='" + mName + '\'' +
                ", vin=" + vin +
                ", dealers=" + dealers +
                '}';
    }
}

