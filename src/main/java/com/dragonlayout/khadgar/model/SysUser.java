package com.dragonlayout.khadgar.model;

import com.dragonlayout.khadgar.model.enumerated.SexType;
import com.dragonlayout.khadgar.model.enumerated.StatusType;
import com.dragonlayout.khadgar.model.enumerated.UserType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Getter
@Setter
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "sys_user")
public class SysUser implements Serializable {
    private static final long serialVersionUID = 5193457739758024688L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "dept_id")
    private Long deptId;

    @Column(name = "position_id")
    private Long positionId;

    @Column(name = "role_id")
    private Long roleId;

    @Size(max = 64)
    @NotNull
    @Column(name = "username", nullable = false, length = 64)
    private String username;

    @Size(max = 32)
    @NotNull
    @Column(name = "nickname", nullable = false, length = 32)
    private String nickname;

    @Enumerated
    @Column(name = "user_type")
    private UserType userType;

    @Size(max = 128)
    @Column(name = "email", length = 128)
    private String email;

    @Size(max = 18)
    @Column(name = "phone_number", length = 18)
    private String phoneNumber;

    @Enumerated
    @Column(name = "sex", columnDefinition = "TINYINT UNSIGNED")
    private SexType sex;

    @Size(max = 512)
    @Column(name = "avatar", length = 512)
    private String avatar;

    @Size(max = 128)
    @NotNull
    @Column(name = "password", nullable = false, length = 128)
    private String password;

    @Enumerated
    @Column(name = "status", columnDefinition = "TINYINT UNSIGNED not null")
    private StatusType status;

    @Size(max = 128)
    @Column(name = "login_ip", length = 128)
    private String loginIp;

    @Column(name = "login_time")
    private Long loginTime;

    @Column(name = "creator_id")
    private Long creatorId;

    @Size(max = 64)
    @Column(name = "creator_name", length = 64)
    private String creatorName;

    @NotNull
    @Column(name = "create_time", nullable = false)
//    @CreatedDate
    private Long createTime;

    @Column(name = "updater_id")
    @LastModifiedBy
    private Long updaterId;

    @Size(max = 64)
    @Column(name = "updater_name", length = 64)
    @LastModifiedBy
    private String updaterName;

    @NotNull
    @Column(name = "update_time", nullable = false)
    @LastModifiedDate
    private Long updateTime;

    @Size(max = 512)
    @Column(name = "remark", length = 512)
    private String remark;

    @Column(name = "is_deleted")
    private Long deleted;

}