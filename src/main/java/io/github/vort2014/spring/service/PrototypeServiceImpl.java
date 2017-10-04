package io.github.vort2014.spring.service;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Created on 17.05.2017.
 */
@Service
@Scope(scopeName = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class PrototypeServiceImpl implements PrototypeService {

    private int randomNumber = ThreadLocalRandom.current().nextInt();

    @Override
    public int getRandomNumber() {
        return randomNumber;
    }
}
