package com.andon.springbootspringdatajpa;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.andon.springbootspringdatajpa.repository.DataSetRepository;
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
