package com.andon.springbootspringdatajpa.repository;

import com.andon.springbootspringdatajpa.domain.EntityDataSet;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Andon
 * 2022/2/10
 */
public interface DataSetRepository extends JpaRepository<EntityDataSet, String> {
}
