package com.oodmi.configuration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;

public class JsonObjectMapper {

    private static final ObjectMapper OBJECT_MAPPER = JsonMapper.builder()
            .enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS)
            .enable(MapperFeature.DEFAULT_VIEW_INCLUSION)
            .enable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
            .enable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
            .enable(SerializationFeature.INDENT_OUTPUT)
            .build();

    private static JsonObjectMapper instance = null;

    private JsonObjectMapper() {
    }

    public static JsonObjectMapper getInstance() {
        if (instance == null) {
            instance = new JsonObjectMapper();
        }
        return instance;
    }

    public String asString(Object object) {
        try {
            return OBJECT_MAPPER.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public <T> T toObject(String jsonAsString, Class<T> clazz) {
        try {
            return OBJECT_MAPPER.readValue(jsonAsString, clazz);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
