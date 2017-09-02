package com.taotao.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;

public class JsonUtils {


    private static final ObjectMapper mapper = new ObjectMapper();


    public static String objectToJson(Object object) throws JsonProcessingException {

        String writeValueAsString = mapper.writeValueAsString(object);
        return writeValueAsString;

    }

    public static <T> T jsonToPojo(String json, Class<T> classType) throws IOException {
        T t = mapper.readValue(json, classType);
        return t;


    }

    public static <T> List<T> jsonToList(String json, Class<T> classType) throws IOException {

        JavaType javaType = mapper.getTypeFactory().constructParametricType(List.class, classType);


        List<T> list = mapper.readValue(json, javaType);
        return list;

    }

}
