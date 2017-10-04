package io.github.vort2014.spring.repositories;

import io.github.vort2014.spring.entities.CompanyEntity;
import org.springframework.data.repository.CrudRepository;

/**
 * Created on 22.09.2017.
 */
public interface CompanyRepository extends CrudRepository<CompanyEntity, Long> {
}
