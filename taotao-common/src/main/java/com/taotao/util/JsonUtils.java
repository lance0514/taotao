package com.taotao.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;

public class JsonUtils {


    private static final ObjectMapper mapper = new ObjectMapper();


    public static String objectToJson(Object object) {
        try {
            String writeValueAsString = mapper.writeValueAsString(object);
            return writeValueAsString;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static <T> T jsonToPojo(String json, Class<T> classType) {
        try {
            T t = mapper.readValue(json, classType);
            return t;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static <T> List<T> jsonToList(String json, Class<T> classType) {

        JavaType javaType = mapper.getTypeFactory().constructParametricType(List.class, classType);

        try {
            List<T> list = mapper.readValue(json, javaType);
            return list;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

}
