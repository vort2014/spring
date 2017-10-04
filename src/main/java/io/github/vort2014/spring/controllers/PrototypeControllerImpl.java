package io.github.vort2014.spring.controllers;

import io.github.vort2014.spring.service.PrototypeService;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created on 17.05.2017.
 */
@RestController
public abstract class PrototypeControllerImpl implements PrototypeController {

    @Override
    @GetMapping("/randomnumber")
    public int getRandomNumber() {
        return createPrototypeService().getRandomNumber();
    }

    @Lookup
    protected abstract PrototypeService createPrototypeService();

//    protected PrototypeService createPrototypeService() {
//        return context.getBean(PrototypeService.class);
//    };
}
