package io.github.vort2014.spring.controllers;

import io.github.vort2014.spring.entities.CustomerEntity;
import io.github.vort2014.spring.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created on 25.05.2017.
 */
@RestController
public class CustomerControllerImpl implements CustomerController {

    @Autowired
    private CustomerService customerService;

    @Override
    @GetMapping("/customers")
    public Iterable<CustomerEntity> findAll() {
        return customerService.findAll();
    }

    @Override
    @PostMapping("/customers")
    public void save(@RequestBody CustomerEntity customerEntity) {
        customerService.save(customerEntity);
    }
}
