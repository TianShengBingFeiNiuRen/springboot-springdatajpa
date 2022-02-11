package com.andon.springbootspringdatajpa.domain;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Andon
 * 2022/2/10
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "data_set")
@FieldDefaults(level = AccessLevel.PRIVATE)
@EntityListeners(AuditingEntityListener.class)
public class EntityDataSet {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "VARCHAR(36) COMMENT 'id'", updatable = false, nullable = false, unique = true)
    String id;

    @Column(columnDefinition = "VARCHAR(255) COMMENT '名称'", nullable = false, unique = true)
    String name;

//    @Enumerated(EnumType.STRING)
//    @Column(columnDefinition = "VARCHAR(100) COMMENT '数据可见性: SELF -> 仅自己 ALL -> 所有人'", nullable = false)
//    VisibleLevel visibleLevel;

    @Column(columnDefinition = "VARCHAR(100) COMMENT '数据可见性: SELF -> 仅自己 ALL -> 所有人'", nullable = false)
    String visibleLevel;

    @Column(columnDefinition = "TINYINT(1) DEFAULT 0 COMMENT '有效性' ", nullable = false)
    boolean available;

    @Column(columnDefinition = "VARCHAR(1000) COMMENT '描述'")
    String description;

    @Column(columnDefinition = "VARCHAR(1000) COMMENT '备注'")
    String note;

    @CreatedDate
    @Column(updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    Date createTime;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    Date updateTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "creator_id", updatable = false)
    EntityUser creator;
}
