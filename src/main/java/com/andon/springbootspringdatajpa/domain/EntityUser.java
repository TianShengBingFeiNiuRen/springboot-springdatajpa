package com.andon.springbootspringdatajpa.domain;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author Andon
 * 2022/2/11
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user")
@FieldDefaults(level = AccessLevel.PRIVATE)
@EntityListeners(AuditingEntityListener.class)
public class EntityUser implements Serializable {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "VARCHAR(36) COMMENT '用户 id'", updatable = false, nullable = false, unique = true)
    String id;

    @Column(columnDefinition = "VARCHAR(255) COMMENT '用户名称'")
    String username;

    @Column(columnDefinition = "VARCHAR(255) COMMENT '用户真实名称'")
    String realName;

    @Column(columnDefinition = "VARCHAR(25) COMMENT '用户手机号码'")
    String phoneNumber;

    @Column(columnDefinition = "VARCHAR(255) COMMENT '用户邮箱'")
    String mail;

    @CreatedDate
    @Column(updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    Date createTime;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    Date updateTime;

//    @ManyToOne
//    @JoinTable(name = "t_user_role_rel",
//            joinColumns = {@JoinColumn(name = "user_id")},
//            inverseJoinColumns = {@JoinColumn(name = "role_id")})
//    Role role;
//
//    @ManyToOne
//    @JoinTable(name = "t_user_organization_rel",
//            joinColumns = {@JoinColumn(name = "user_id")},
//            inverseJoinColumns = {@JoinColumn(name = "organization_id")})
//    Organization organization;
}
