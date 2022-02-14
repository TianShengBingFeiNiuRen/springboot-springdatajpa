package com.andon.springbootspringdatajpa;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.andon.springbootspringdatajpa.repository.DataSetRepository;
import com.andon.springbootspringdatajpa.util.SerializationUtil;
import com.andon.springbootspringdatajpa.vo.VoDataSet;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Andon
 * 2022/2/13
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class DatabaseTest {

    @Resource
    private DataSetRepository repository;

    @Test
    public void test03() {
        List<Map<String, Object>> maps = repository.test2();
        log.info("maps:{}", maps);
        byte[] serialize = SerializationUtil.serialize(maps);
        String serializeString = new String(serialize);
        log.info("serializeString:{}", serializeString);
        List<VoDataSet> voDataSetList = SerializationUtil.deserialize(serialize, new com.fasterxml.jackson.core.type.TypeReference<List<VoDataSet>>() {
        });
        log.info("voDataSetList:{}", voDataSetList);
        byte[] bytes = SerializationUtil.serialize(voDataSetList);
        String s = new String(bytes);
        log.info("s:{}", s);
    }

    @Test
    public void test02() {
        // TODO 返回json tinyint 0,1转boolean
        // TODO 返回json 字符串 转 枚举
        // TODO 返回json datetime 转 Date
        List<Map<String, Object>> maps = repository.test2();
        String jsonString = JSONObject.toJSONString(maps);
        log.info("maps:{}", jsonString);
        List<VoDataSet> voDataSetList = maps.stream()
                .map(map -> JSONObject.<VoDataSet>parseObject(JSONObject.toJSONString(map), new TypeReference<VoDataSet>() {
                }.getType()))
                .collect(Collectors.toList());
        log.info("voDataSetList:{}", JSONObject.toJSONString(voDataSetList));
    }

    @Test
    public void test01() {
        List<Map<String, Object>> test = repository.test();
        log.info("test:{}", JSONObject.toJSONString(test));
        List<T> tList = test.stream()
                .map(stringObjectMap -> {
                    T t = new T();
//                    BeanUtils.copyProperties(stringObjectMap, t);
                    t = JSONObject.parseObject(JSONObject.toJSONString(stringObjectMap), new TypeReference<T>() {
                    }.getType());
                    return t;
                })
                .collect(Collectors.toList());
        log.info("tList:{}", JSONObject.toJSONString(tList));
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @FieldDefaults(level = AccessLevel.PRIVATE)
    static class T {
        String id;
        String name;
    }
}
