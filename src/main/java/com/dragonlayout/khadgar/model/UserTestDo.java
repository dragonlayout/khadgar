package com.dragonlayout.khadgar.model;

import com.dragonlayout.khadgar.model.base.AbstractAuditModel;
import com.dragonlayout.khadgar.model.enumerated.SexType;
import com.dragonlayout.khadgar.model.enumerated.StatusType;
import com.dragonlayout.khadgar.model.enumerated.UserType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import java.io.Serializable;

//@Builder
@Getter
@Setter
@Entity
@Table(name = "user_test")
public class UserTestDo extends AbstractAuditModel implements Serializable{
    private static final long serialVersionUID = 4790930734743986293L;

    //    @Size(max = 64)
//    @NotNull
    @Column(name = "username") private String username;

    @Enumerated
    @Column(name = "user_type")
    private UserType userType = UserType.ADMIN;

    @Enumerated
    @Column(name = "sex")
    private SexType sex = SexType.MALE;

//    @Size(max = 128)
//    @NotNull
    @Column(name = "password")
    private String password;

    @Enumerated
    @Column(name = "status")
    private StatusType status = StatusType.ENABLED;

}