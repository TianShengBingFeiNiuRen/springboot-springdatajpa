package com.andon.springbootspringdatajpa.service;

import com.andon.springbootspringdatajpa.domain.EntityDataSet;
import com.andon.springbootspringdatajpa.repository.DataSetRepository;
import com.andon.springbootspringdatajpa.vo.VoDataSet;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author Andon
 * 2022/2/10
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class DataSetService {

    private final DataSetRepository dataSetRepository;

    public VoDataSet add(VoDataSet voDataSet) {
        // TODO RequestBody接收枚举类型
        EntityDataSet entityDataSet = new EntityDataSet();
        BeanUtils.copyProperties(voDataSet, entityDataSet);
        BeanUtils.copyProperties(dataSetRepository.save(entityDataSet), voDataSet);
        return voDataSet;
    }

    public VoDataSet update(VoDataSet voDataSet) {
        Optional<EntityDataSet> byId = dataSetRepository.findById(voDataSet.getId());
        byId.ifPresent(entityDataSet -> {
            BeanUtils.copyProperties(voDataSet, entityDataSet);
            BeanUtils.copyProperties(dataSetRepository.save(entityDataSet), voDataSet);
        });
        return voDataSet;
    }

    public VoDataSet query(String id) {
        VoDataSet voDataSet = new VoDataSet();
        Optional<EntityDataSet> byId = dataSetRepository.findById(id);
        byId.ifPresent(entityDataSet2 -> BeanUtils.copyProperties(entityDataSet2, voDataSet));
        return voDataSet;
    }

    public void pageQuery() {
        // TODO 动态条件分页查询
    }
}
