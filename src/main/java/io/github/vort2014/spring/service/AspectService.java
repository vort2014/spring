package io.github.vort2014.spring.service;

import java.util.concurrent.TimeUnit;

/**
 * Created on 24.05.2017.
 */
public interface AspectService {

    void log(String fullMethodName, long time, TimeUnit timeUnit);
}
