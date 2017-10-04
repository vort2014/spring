package io.github.vort2014.spring.service;

import io.github.vort2014.spring.entities.CustomerEntity;
import io.github.vort2014.spring.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created on 25.05.2017.
 */
@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Iterable<CustomerEntity> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public void save(CustomerEntity customerEntity) {
        customerRepository.save(customerEntity);
    }
}
