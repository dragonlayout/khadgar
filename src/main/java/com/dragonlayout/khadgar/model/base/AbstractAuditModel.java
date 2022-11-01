package com.dragonlayout.khadgar.model.base;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

@Setter
@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AbstractAuditModel extends AbstractBaseModel {

    @Column(name = "creator_id")
    @CreatedBy
    private Long creatorId;

    @Column(name = "updater_id")
    @LastModifiedBy
    private Long updaterId;
}
