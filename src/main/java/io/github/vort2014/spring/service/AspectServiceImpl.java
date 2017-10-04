package io.github.vort2014.spring.service;

import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * Created on 24.05.2017.
 */
@Service
public class AspectServiceImpl implements AspectService {

    @Override
    public void log(String fullMethodName, long time, TimeUnit timeUnit) {
        System.out.println(fullMethodName + " " + time + " " + timeUnit.toString().toLowerCase());
    }
}
