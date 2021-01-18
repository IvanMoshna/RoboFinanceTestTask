package com.testtask.robofinance.controller;

import com.testtask.robofinance.domain.Address;
import com.testtask.robofinance.domain.Customer;
import com.testtask.robofinance.repos.AddressRepo;
import com.testtask.robofinance.repos.CustomerRepo;
import com.testtask.robofinance.service.MainService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
public class MainController {
    private final AddressRepo addressRepo;
    private final CustomerRepo customerRepo;
    private MainService mainService;

    public static final String MAIN_PAGE = "main";
    public static final String CUSTOMER_DETAILS = "customer-details";
    public static final String HOME_PAGE = "home";

    public MainController(AddressRepo addressRepo, CustomerRepo customerRepo,
                          MainService mainService) {
        this.addressRepo = addressRepo;
        this.customerRepo = customerRepo;
        this.mainService = mainService;
    }

    @GetMapping("/home")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return HOME_PAGE;
    }

    @GetMapping
    public String main(Model model) {
        List<Customer> customersList = mainService.getAllCustomers();
        model.addAttribute("customers", customersList);
        return MAIN_PAGE;
    }

    //Save new customer
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

        return MAIN_PAGE;
    }

    //Load selected customer
    @GetMapping("{id}")
    public String customerDetails(@PathVariable(value = "id") int id, Model model) throws Exception {
        Customer customer = customerRepo.findById(id).orElseThrow(() -> new Exception("Customer not found - " + id));
        Address actualAddress = addressRepo.findById(customer.getActual_address()).orElseThrow(() ->
                new Exception("Address not found - " + customer.getActual_address()));

        model.addAttribute("customer", customer);
        model.addAttribute("actualAddress", actualAddress);
        return CUSTOMER_DETAILS;
    }

    //Update actual address at customer
    @PostMapping("{id}/update")
    public String actualAddressPostUpdate(@PathVariable(value = "id") int id,
                                          @RequestParam String country,
                                          @RequestParam String region,
                                          @RequestParam String city,
                                          @RequestParam String street,
                                          @RequestParam String house,
                                          @RequestParam String flat,
                                          Model model) throws Exception {

        Address address = addressRepo.findById(id).orElseThrow(() ->
                new Exception("Address not found - " + id));

        address.setCountry(country);
        address.setRegion(region);
        address.setCity(city);
        address.setStreet(street);
        address.setHouse(house);
        address.setFlat(flat);
        addressRepo.save(address);

        return HOME_PAGE;
    }
}
