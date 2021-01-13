package com.testtask.robofinance.repos;

import com.testtask.robofinance.domain.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepo extends CrudRepository<Customer, Integer> {
}
