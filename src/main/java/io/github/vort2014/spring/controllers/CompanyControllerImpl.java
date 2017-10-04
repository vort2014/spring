package io.github.vort2014.spring.controllers;

import io.github.vort2014.spring.entities.CompanyEntity;
import io.github.vort2014.spring.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created on 22.09.2017.
 */
@RestController
public class CompanyControllerImpl implements CompanyController {

    @Autowired
    private CompanyService companyService;

    @GetMapping("/companies")
    @Override
    public Iterable<CompanyEntity> findAll() {
        return companyService.findAll();
    }
}
