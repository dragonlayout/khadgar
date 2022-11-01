package com.dragonlayout.khadgar.model.base;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Setter
@Getter
@MappedSuperclass
public abstract class AbstractBaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "create_time")
    @CreatedDate
    private Long createTime;

    @Column(name = "update_time")
    @LastModifiedDate
    private Long updateTime;

    @Column(name = "is_deleted")
    private Long deleted = 0L;
}
