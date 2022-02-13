package com.andon.springbootspringdatajpa.service;

import com.andon.springbootspringdatajpa.domain.EntityDataSet;
import com.andon.springbootspringdatajpa.domain.EntityUser;
import com.andon.springbootspringdatajpa.domain.ResponseStandard;
import com.andon.springbootspringdatajpa.repository.DataSetRepository;
import com.andon.springbootspringdatajpa.vo.VoDataSet;
import com.andon.springbootspringdatajpa.vo.VoDataSetAddReq;
import com.andon.springbootspringdatajpa.vo.VoDataSetPageReq;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.persistence.criteria.Predicate;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Andon
 * 2022/2/10
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class DataSetService {

    private final DataSetRepository dataSetRepository;

    public VoDataSet add(VoDataSetAddReq voDataSetAddReq) {
        VoDataSet voDataSet = new VoDataSet();
        EntityDataSet entityDataSet = new EntityDataSet();
        BeanUtils.copyProperties(voDataSetAddReq, entityDataSet);
        entityDataSet.setCreator(EntityUser.builder().id(voDataSetAddReq.getCreator_id()).build());
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

    public ResponseStandard<List<VoDataSet>> pageQuery(VoDataSetPageReq voDataSetPageReq) {
        List<VoDataSet> list = new ArrayList<>();
        Specification<EntityDataSet> specification = (root, query, cb) -> {
            List<Predicate> predicateList = new ArrayList<>();
            if (!ObjectUtils.isEmpty(voDataSetPageReq.getName())) {
                predicateList.add(cb.like(root.get("name"), "%" + voDataSetPageReq.getName() + "%"));
            }
            if (!ObjectUtils.isEmpty(voDataSetPageReq.getDescription())) {
                predicateList.add(cb.equal(root.get("description"), voDataSetPageReq.getDescription()));
            }
            // TODO 日期处理
            if (!ObjectUtils.isEmpty(voDataSetPageReq.getCreateTime())) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String createTime = voDataSetPageReq.getCreateTime();
                Date parse = new Date();
                try {
                    parse = simpleDateFormat.parse(createTime);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                predicateList.add(cb.greaterThanOrEqualTo(root.get("createTime"), parse));
            }
            // TODO in条件
//            if (voDataSetPageReq.getCreatorId() != null) {
//                String[] split = voDataSetPageReq.getCreatorId().split(",");
////                CriteriaBuilder.In<Object> in = cb.in(root.get("creator_id"));
////                Arrays.stream(split).forEach(in::value);
////                predicateList.add(in);
//                predicateList.add(cb.in(root.get("creator_id")).value(split));
//            }
            // TODO join操作
            Predicate[] predicates = new Predicate[predicateList.size()];
            return query.where(predicateList.toArray(predicates)).getRestriction();
        };
        PageRequest pageRequest = PageRequest.of(voDataSetPageReq.getPage(), voDataSetPageReq.getSize(), Sort.Direction.DESC, "createTime");
        Page<EntityDataSet> all = dataSetRepository.findAll(specification, pageRequest);
        List<EntityDataSet> content = all.getContent();
        long count = dataSetRepository.count(specification);
        list = content.stream()
                .map(entityDataSet -> {
                    VoDataSet dataSet = new VoDataSet();
                    BeanUtils.copyProperties(entityDataSet, dataSet);
                    return dataSet;
                })
                .collect(Collectors.toList());
        ResponseStandard<List<VoDataSet>> response = ResponseStandard.successResponse(list);
        response.setTotal(count);
        return response;
    }
}
