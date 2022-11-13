package com.inventory.management.helper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Class to handle the Json files
 */
public class JsonFileHelper {

    /**
     * Hide the constructor
     */
    private JsonFileHelper() {
    }

    /**
     * Convert json object to object
     */
    public static final <T> T fromJsonToObject(String json, Class<T> beanClass) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        T object = null;
        if (beanClass != null && json != null) {
            object = mapper.readValue(json, beanClass);
        }
        return object;
    }

}
