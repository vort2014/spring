package io.github.vort2014.spring.controllers;

import io.github.vort2014.spring.aspect.LogMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created on 24.05.2017.
 */
@RestController
public class HelloControllerImpl implements HelloController {

    @Override
    @GetMapping("/hello")
    public String hello() {
        logCallPrivateStaticMethod();
        return "Hello World";
    }

    @LogMethod
    private static void logCallPrivateStaticMethod() {
        System.out.println("true = " + true);
    }
}
