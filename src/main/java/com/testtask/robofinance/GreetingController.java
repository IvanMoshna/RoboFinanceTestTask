package com.testtask.robofinance;

import com.testtask.robofinance.domain.Address;
import com.testtask.robofinance.domain.Customer;
import com.testtask.robofinance.repos.AddressRepo;
import com.testtask.robofinance.repos.CustomerRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

@Controller
public class GreetingController {
    private final AddressRepo addressRepo;
    private final CustomerRepo customerRepo;

    public GreetingController(AddressRepo addressRepo, CustomerRepo customerRepo) {
        this.addressRepo = addressRepo;
        this.customerRepo = customerRepo;
    }

    @GetMapping("/greeting")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting.html";
    }

    @GetMapping
    public String main(Model model) {
        return "main";
    }

    @PostMapping
    public String addCustomer(@RequestParam String first_name,
                              @RequestParam String second_name,
                              @RequestParam String middle_name,
                              @RequestParam String sex,
                              @RequestParam String country,
                              @RequestParam String region,
                              @RequestParam String city,
                              @RequestParam String street,
                              @RequestParam String house,
                              @RequestParam String flat,
                              Model model) {
        Date createdTime = new Date(System.currentTimeMillis());
        Address address = new Address(country, region, city, street, house, flat, createdTime);
        addressRepo.save(address);
        Customer customer = new Customer(first_name, second_name, middle_name, address.getId(), address.getId(), sex);

        customerRepo.save(customer);

        Iterable<Customer> customers = customerRepo.findAll();
        model.addAttribute("customers", customers);

        return "main";
    }
}
