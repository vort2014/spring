package io.github.vort2014.spring.repositories;

import io.github.vort2014.spring.entities.CustomerEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created on 04.10.2017.
 */
public interface CustomerRepository extends PagingAndSortingRepository<CustomerEntity, Long> {

    Iterable<CustomerEntity> findByFirstNameAndLastName(String firstName, String lastName);
}
