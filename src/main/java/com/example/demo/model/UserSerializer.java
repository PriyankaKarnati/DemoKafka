package com.example.demo.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.serialization.Serializer;
import org.springframework.util.SerializationUtils;

import java.io.Serializable;
import java.util.Map;
//
//interface Serializer extends Closeable {
//    void configure(Map<String, ?> var1, boolean var2);
//    byte[] serialize(String var1, User var2);
//    void close();
//}
public class UserSerializer<T extends Serializable> implements Serializer{


//    @Override
//    public byte[] serialize(String topic, Object data) {
//        return new byte[0];
//    }

    @Override
    public byte[] serialize(String topic, Object data) {
        byte[] retVal = null;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            retVal = objectMapper.writeValueAsString(data).getBytes();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return retVal;
    }

    @Override
    public byte[] serialize(String topic, Headers headers, Object data) {
        return new byte[0];
    }

    @Override
    public void close() {
    }
//    @Override
//    public byte[] serialize(String topic, Object data) {
//        byte[] retVal = null;
//        ObjectMapper objectMapper = new ObjectMapper();
//        try {
//            retVal = objectMapper.writeValueAsString(data).getBytes();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return retVal;
//
//    }
//
//    @Override
//    public void configure(Map configs, boolean isKey) {
//
//    }
//
//    @Override
//    public byte[] serialize(String topic, Headers headers, Object data) {
//        return new byte[0];
//    }
//
//    @Override
//    public void close() {
//
//    }
//        @Override public void configure(Map<String, ?> map, boolean b) {
//    }


//    @Override public byte[] serialize(String arg0, User arg1) {
//        byte[] retVal = null;
//        ObjectMapper objectMapper = new ObjectMapper();
//        try {
//            retVal = objectMapper.writeValueAsString(arg1).getBytes();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return retVal;
//    }
//    @Override public void close() {
//    }
}