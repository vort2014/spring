package io.github.vort2014.spring.controllers;

import io.github.vort2014.spring.entities.CompanyEntity;

/**
 * Created on 22.09.2017.
 */
public interface CompanyController {

    Iterable<CompanyEntity> findAll();
}
