package com.andon.springbootspringdatajpa.controller;

import com.andon.springbootspringdatajpa.domain.ResponseStandard;
import com.andon.springbootspringdatajpa.service.DataSetService;
import com.andon.springbootspringdatajpa.vo.VoDataSet;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author Andon
 * 2022/2/10
 */
@RestController("data-set")
@RequiredArgsConstructor
public class DataSetController {

    private final DataSetService dataSetService;

    @PostMapping("add")
    public ResponseStandard<VoDataSet> add(@Valid @RequestBody VoDataSet voDataSet) {
        return ResponseStandard.successResponse(dataSetService.add(voDataSet));
    }

    @GetMapping("query")
    public ResponseStandard<VoDataSet> query(String id) {
        return ResponseStandard.successResponse(dataSetService.query(id));
    }

    @PostMapping("update")
    public ResponseStandard<VoDataSet> update(@Valid @RequestBody VoDataSet voDataSet) {
        return ResponseStandard.successResponse(dataSetService.update(voDataSet));
    }
}
