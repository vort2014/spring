package io.github.vort2014.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;

/**
 * Created by vort on 09.07.2017.
 * We need to put this configuration in the root of project
 * this way we don't need to use @ComponentScan
 * http://docs.spring.io/spring-boot/docs/current/reference/html/using-boot-structuring-your-code.html
 */
@SpringBootApplication  // same as @Configuration @EnableAutoConfiguration @ComponentScan @EnableJpaRepositories
@EnableSpringConfigured // for injection context into AnnotationBeanConfigurerAspect
public class Application {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }
}
