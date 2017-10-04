package io.github.vort2014.spring.controllers;

import io.github.vort2014.spring.entities.CustomerEntity;

import java.util.List;

/**
 * Created on 25.05.2017.
 */
public interface CustomerController {

    Iterable<CustomerEntity> findAll();

    void save(CustomerEntity customerEntity);
}
