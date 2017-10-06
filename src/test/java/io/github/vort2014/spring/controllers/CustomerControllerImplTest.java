package io.github.vort2014.spring.controllers;

import io.github.vort2014.spring.ApplicationIT;
import io.github.vort2014.spring.entities.CustomerEntity;
import io.github.vort2014.spring.repositories.CustomerRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import java.time.Instant;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by vort on 09.07.2017.
 */
public class CustomerControllerImplTest extends ApplicationIT {

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    public void testFindAll() throws Exception {
        String json = getJson("CustomerControllerImplTest_testFindAll.json");
        mockMvc.perform(MockMvcRequestBuilders.get("/customers"))
                .andExpect(MockMvcResultMatchers.content().json(json))
                .andDo(MockMvcResultHandlers.print())
                .andDo(document(PATH, preprocessResponse(prettyPrint()), responseFields(   // add to "home" directory snippets for restdocs
                        fieldWithPath("[].customerId").description("Customer id"),
                        fieldWithPath("[].email").description("Customer Email"),
                        fieldWithPath("[].firstName").description("Customer name"),
                        fieldWithPath("[].lastName").description("Customer last name"),
                        fieldWithPath("[].phone").description("Customer phone"),
                        fieldWithPath("[].customerDate").description("Customer birthday"),
                        fieldWithPath("[].companyEntity").description("Company object")
                )));
    }

    @Test
    public void testSave() throws Exception {
        // setup
        CustomerEntity expectedCustomerEntity = new CustomerEntity();
        expectedCustomerEntity.setFirstName("Ivan");
        expectedCustomerEntity.setLastName("Ivanovich");
        expectedCustomerEntity.setEmail("ivan@mail.ru");
        expectedCustomerEntity.setPhone("555-340-1230");
        expectedCustomerEntity.setCustomerDate(Instant.now());

        // run
        String json = objectMapper.writeValueAsString(expectedCustomerEntity);
        mockMvc.perform(post("/customers").content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(HttpStatus.OK.value())); // status is desirably to check

        // verify
        CustomerEntity actualCustomerEntity = customerRepository.findByFirstNameAndLastName("Ivan", "Ivanovich").iterator().next();
        assertThat(expectedCustomerEntity).isEqualTo(actualCustomerEntity);
    }
}