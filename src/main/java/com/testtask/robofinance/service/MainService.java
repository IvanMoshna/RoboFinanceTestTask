package com.testtask.robofinance.service;

import com.testtask.robofinance.domain.Customer;
import com.testtask.robofinance.repos.AddressRepo;
import com.testtask.robofinance.repos.CustomerRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MainService {
    private final AddressRepo addressRepo;
    private final CustomerRepo customerRepo;

    public MainService(AddressRepo addressRepo, CustomerRepo customerRepo) {
        this.addressRepo = addressRepo;
        this.customerRepo = customerRepo;
    }

    public List<Customer> getAllCustomers() {
        Iterable<Customer> customers = customerRepo.findAll();
        List<Customer> customerList = new ArrayList<>();
        for (Customer c : customers) {
            customerList.add(new Customer(c.getId(), c.getRegistered_address(), c.getActual_address(),
                    c.getFirst_name(), c.getLast_name(), c.getMiddle_name(), c.getSex()));
        }
        return customerList;
    }
}
