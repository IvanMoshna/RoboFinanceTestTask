package com.testtask.robofinance;

import com.testtask.robofinance.domain.Address;
import com.testtask.robofinance.domain.Customer;
import com.testtask.robofinance.repos.AddressRepo;
import com.testtask.robofinance.repos.CustomerRepo;
import com.testtask.robofinance.service.MainService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

@Controller
public class GreetingController {
    private final AddressRepo addressRepo;
    private final CustomerRepo customerRepo;
    private MainService mainService;

    public GreetingController(AddressRepo addressRepo, CustomerRepo customerRepo,
                              MainService mainService) {
        this.addressRepo = addressRepo;
        this.customerRepo = customerRepo;
        this.mainService = mainService;
    }

    @GetMapping("/greeting")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting.html";
    }

    @GetMapping
    public String main(Model model) {
        List<Customer> customersList = mainService.getAllCustomers();
        model.addAttribute("customers", customersList);
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
                              @RequestParam String actualCountry,
                              @RequestParam String actualRegion,
                              @RequestParam String actualCity,
                              @RequestParam String actualStreet,
                              @RequestParam String actualHouse,
                              @RequestParam String actualFlat,
                              Model model) {
        Date createdTime = new Date(System.currentTimeMillis());
        Address registredAddress = new Address(country, region, city, street, house, flat, createdTime);
        Address actualAddress = new Address(actualCountry, actualRegion, actualCity, actualStreet, actualHouse,
                actualFlat,createdTime);
        addressRepo.save(registredAddress);
        addressRepo.save(actualAddress);
        Customer customer = new Customer(first_name, second_name, middle_name, registredAddress.getId(), actualAddress.getId(), sex);

        customerRepo.save(customer);

        Iterable<Customer> customers = customerRepo.findAll();
        model.addAttribute("customers", customers);

        return "main";
    }
}
