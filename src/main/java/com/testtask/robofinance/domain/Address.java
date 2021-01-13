package com.testtask.robofinance.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String region;
    private String city;
    private String street;
    private String house;
    private String flat;
    private Date created;
    private Date modified;

    public Address() {
    }

    public Address(Integer id, String region, String city, String street, String house, String flat, Date created, Date modified) {
        this.id = id;
        this.region = region;
        this.city = city;
        this.street = street;
        this.house = house;
        this.flat = flat;
        this.created = created;
        this.modified = modified;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public void setFlat(String flat) {
        this.flat = flat;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }

    public Integer getId() {
        return id;
    }

    public String getRegion() {
        return region;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public String getHouse() {
        return house;
    }

    public String getFlat() {
        return flat;
    }

    public Date getCreated() {
        return created;
    }

    public Date getModified() {
        return modified;
    }
}
