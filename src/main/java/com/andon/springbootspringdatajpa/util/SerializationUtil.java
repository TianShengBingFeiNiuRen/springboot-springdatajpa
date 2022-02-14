package com.andon.springbootspringdatajpa.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * @author Luva
 * @description TODO
 * @date 2022/2/5 17:26
 * @since 0.0.1
 */
@Slf4j
public final class SerializationUtil {

    private final static ObjectMapper mapper = new ObjectMapper();

    static {
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    private SerializationUtil() {
    }

    /**
     * 序列化(对象 -> 字节数组)
     *
     * @param obj 对象
     * @return 字节数组
     */
    public static <T> byte[] serialize(T obj) {
        try {
            return mapper.writeValueAsBytes(obj);
        } catch (JsonProcessingException e) {
            log.error("序列化失败: ", e);
            throw new IllegalStateException(e.getMessage(), e);
        }
    }

    /**
     * 反序列化(字节数组 -> 对象)
     *
     * @param data
     * @param valueTypeRef
     * @param <T>
     */
    public static <T> T deserialize(byte[] data, TypeReference<T> valueTypeRef) {
        try {
            return mapper.readValue(data, valueTypeRef);
        } catch (IOException e) {
            log.error("反序列化失败: ", e);
            throw new IllegalStateException(e.getMessage(), e);
        }
    }

    /**
     * 反序列化(字节数组 -> 对象)
     *
     * @param data
     * @param cls
     * @param <T>
     */
    public static <T> T deserialize(byte[] data, Class<T> cls) {
        try {
            return mapper.readValue(data, cls);
        } catch (IOException e) {
            log.error("反序列化失败: ", e);
            throw new IllegalStateException(e.getMessage(), e);
        }
    }

    /**
     * 反序列化(字节数组 -> 对象)
     *
     * @param jsonStr
     * @param cls
     * @param <T>
     */
    public static <T> T deserialize(String jsonStr, Class<T> cls) {
        try {
            return mapper.readValue(jsonStr, cls);
        } catch (IOException e) {
            log.error("反序列化失败: ", e);
            throw new IllegalStateException(e.getMessage(), e);
        }
    }

}
