package io.github.vort2014.spring.service;

import io.github.vort2014.spring.entities.CompanyEntity;
import io.github.vort2014.spring.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created on 22.09.2017.
 */
@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    @Override
    public Iterable<CompanyEntity> findAll() {
        return companyRepository.findAll();
    }
}
