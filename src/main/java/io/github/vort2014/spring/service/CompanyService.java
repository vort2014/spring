package io.github.vort2014.spring.service;

import io.github.vort2014.spring.entities.CompanyEntity;

/**
 * Created on 22.09.2017.
 */
public interface CompanyService {

    Iterable<CompanyEntity> findAll();
}
