package com.example.demo.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Deserializer;

import java.io.Closeable;
import java.io.Serializable;
import java.util.Map;


public class UserDeserializer<T extends Serializable>  implements Deserializer {
    @Override public void close() {
    }

    @Override
    public Object deserialize(String arg0, byte[] arg1) {
        ObjectMapper mapper = new ObjectMapper();
        Object user = null;
        try {
            user = mapper.readValue(arg1, Object.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

}
