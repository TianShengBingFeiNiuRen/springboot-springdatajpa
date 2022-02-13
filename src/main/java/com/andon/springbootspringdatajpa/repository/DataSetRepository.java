package com.andon.springbootspringdatajpa.repository;

import com.andon.springbootspringdatajpa.domain.EntityDataSet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

/**
 * @author Andon
 * 2022/2/10
 */
public interface DataSetRepository extends JpaRepository<EntityDataSet, String>, JpaSpecificationExecutor<EntityDataSet> {

    @Query(nativeQuery = true, value = "SELECT `id`,`name` FROM `data_set` ORDER BY `create_time`;")
    List<Map<String, Object>> test();
}
