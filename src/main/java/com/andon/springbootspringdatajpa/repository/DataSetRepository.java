package com.andon.springbootspringdatajpa.repository;

import com.andon.springbootspringdatajpa.domain.EntityDataSet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author Andon
 * 2022/2/10
 */
public interface DataSetRepository extends JpaRepository<EntityDataSet, String>, JpaSpecificationExecutor<EntityDataSet> {
}
