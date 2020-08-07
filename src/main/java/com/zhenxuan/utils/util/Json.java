//package com.zhenxuan.utils.util;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.core.type.TypeReference;
//import com.fasterxml.jackson.databind.JavaType;
//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.node.ArrayNode;
//import com.fasterxml.jackson.databind.node.ObjectNode;
//
//import java.io.IOException;
//import java.io.UncheckedIOException;
//import java.net.URL;
//import java.util.concurrent.ConcurrentHashMap;
//
///**
// * Json工具类(对jackson的简单封装)
// */
//public final class Json {
//    public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
//    private static final ConcurrentHashMap<String, JavaType> JAVA_TYPE_CACHE = new ConcurrentHashMap<>();
//
//    public static JsonNode parse(URL url) {
//        try {
//            return OBJECT_MAPPER.readTree(url);
//        } catch (IOException e) {
//            throw new UncheckedIOException(e);
//        }
//    }
//
//    public static JsonNode parse(String json) {
//        try {
//            return OBJECT_MAPPER.readTree(json);
//        } catch (IOException e) {
//            throw new UncheckedIOException(e);
//        }
//    }
//
//    public static <T> T parse(String json, Class<T> type) {
//        try {
//            return OBJECT_MAPPER.readValue(json, type);
//        } catch (IOException e) {
//            throw new UncheckedIOException(e);
//        }
//    }
//
//    public static <T> T parse(String json, TypeReference<T> typeRef) {
//        try {
//            return OBJECT_MAPPER.readValue(json, typeRef);
//        } catch (IOException e) {
//            throw new UncheckedIOException(e);
//        }
//    }
//
//    public static <T> T parse(String json, JavaType javaType) {
//        try {
//            return OBJECT_MAPPER.readValue(json, javaType);
//        } catch (IOException e) {
//            throw new UncheckedIOException(e);
//        }
//    }
//
//    public static <T> T parse(String json, String typeCanonical) {
//        return parse(json, getJavaType(typeCanonical));
//    }
//
//    public static String stringify(JsonNode json) {
//        return json.toString();
//    }
//
//    public static String stringify(Object object) {
//        try {
//            return OBJECT_MAPPER.writeValueAsString(object);
//        } catch (JsonProcessingException e) {
//            throw new UncheckedIOException(e);
//        }
//    }
//
//    public static <T> T transform(JsonNode json, Class<T> type) {
//        return OBJECT_MAPPER.convertValue(json, type);
//    }
//
//    public static <T> T transform(JsonNode json, TypeReference<T> typeRef) {
//        return OBJECT_MAPPER.convertValue(json, typeRef);
//    }
//
//    public static <T> T transform(JsonNode json, JavaType javaType) {
//        return OBJECT_MAPPER.convertValue(json, javaType);
//    }
//
//    public static <T> T transform(JsonNode json, String typeCanonical) {
//        return transform(json, getJavaType(typeCanonical));
//    }
//
//    public static JsonNode transform(Object object) {
//        return OBJECT_MAPPER.valueToTree(object);
//    }
//
//    public static ArrayNode newArray() {
//        return OBJECT_MAPPER.createArrayNode();
//    }
//
//    public static ObjectNode newObject() {
//        return OBJECT_MAPPER.createObjectNode();
//    }
//
//    private static JavaType getJavaType(String typeCanonical) {
//        return JAVA_TYPE_CACHE.computeIfAbsent(typeCanonical, OBJECT_MAPPER.getTypeFactory()::constructFromCanonical);
//    }
//}
