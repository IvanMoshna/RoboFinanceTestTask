package com.testtask.robofinance.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private int registred_address_id;
    private int actual_address_id;
    private String first_name;
    private String last_name;
    private String middle_name;
    private String sex;


    public Customer(Integer id, int registred_address_id, int actual_address_id, String first_name, String last_name, String middle_name, String sex) {
        this.id = id;
        this.registred_address_id = registred_address_id;
        this.actual_address_id = actual_address_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.middle_name = middle_name;
        this.sex = sex;
    }

    public Customer(String first_name, String last_name, String middle_name, Integer registred_address,
                    Integer actual_address, String sex) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.middle_name = middle_name;
        this.registred_address_id = registred_address;
        this.actual_address_id = actual_address;
        this.sex = sex;
    }

    public Customer() {
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setRegistered_address(Integer registered_address) {
        this.registred_address_id = registered_address;
    }

    public void setActual_address(Integer actual_address) {
        this.actual_address_id = actual_address;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public void setMiddle_name(String middle_name) {
        this.middle_name = middle_name;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getId() {
        return id;
    }

    public Integer getRegistered_address() {
        return registred_address_id;
    }

    public Integer getActual_address() {
        return actual_address_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getMiddle_name() {
        return middle_name;
    }

    public String getSex() {
        return sex;
    }
}
