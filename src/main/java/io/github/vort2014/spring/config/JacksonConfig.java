package io.github.vort2014.spring.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.io.IOException;
import java.time.Instant;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

/**
 * Created on 04.10.2017.
 */
@Configuration
public class JacksonConfig {

    // to write dates in ISO-8601 format
    @Bean
    @Primary
    public ObjectMapper objectMapper(Jackson2ObjectMapperBuilder jackson2ObjectMapperBuilder) {
        SimpleModule javaTimeModule = new JavaTimeModule()
                .addDeserializer(Instant.class, new JsonDeserializer<Instant>() {
                        @Override
                        public Instant deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
                            return DateTimeFormatter.ISO_LOCAL_DATE_TIME
                                    .withZone(ZoneOffset.UTC)
                                    .parse(p.getText(), Instant::from);
                        }
                })
                .addSerializer(Instant.class, new JsonSerializer<Instant>() {
                        @Override
                        public void serialize(Instant value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
                            gen.writeString(DateTimeFormatter.ISO_LOCAL_DATE_TIME.withZone(ZoneOffset.UTC).format(value));
                        }
                });
        return jackson2ObjectMapperBuilder
                .createXmlMapper(false)
                .build()
//                .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
                .configure(MapperFeature.IGNORE_DUPLICATE_MODULE_REGISTRATIONS, false)
                .registerModule(javaTimeModule);
    }
}
