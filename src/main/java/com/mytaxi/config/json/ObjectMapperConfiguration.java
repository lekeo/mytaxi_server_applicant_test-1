package com.mytaxi.config.json;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 *
 */
@Configuration
public class ObjectMapperConfiguration {
    @Bean
    @Primary
    public ObjectMapper getObjectMapper() {
        final ObjectMapper mapper = new ObjectMapper();

        final SimpleModule dateModule = dateModule();

        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        mapper.registerModule(dateModule);

        return mapper;
    }

    private SimpleModule dateModule() {
        final SimpleModule dateModule = new SimpleModule("DateModule");
        dateModule.addDeserializer(ZonedDateTime.class, new ZonedDateTimeDeserializer());
        dateModule.addSerializer(ZonedDateTime.class, new ZonedDateTimeSerializer());
        return dateModule;
    }

}
