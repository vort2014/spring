package io.github.vort2014.spring.config;

import io.github.vort2014.spring.ApplicationIT;
import io.github.vort2014.spring.entities.CustomerEntity;
import org.junit.Test;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created on 06.10.2017.
 */
public class JacksonConfigTest extends ApplicationIT {

    @Test
    public void testInstant() throws Exception {

        // given
        String expected = getJson("JacksonConfigTest_testInstant.json");
        String date = "2017-10-06T08:42:07.407";
        Instant instant = DateTimeFormatter.ISO_LOCAL_DATE_TIME.withZone(ZoneOffset.UTC).parse(date, Instant::from);
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setCustomerDate(instant);

        // when
        String actual = objectMapper.writeValueAsString(customerEntity);

        // then
        assertThat(actual).isEqualTo(expected);
    }
}