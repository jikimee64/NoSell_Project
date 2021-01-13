package com.soap.moon.domains.member.domain;

import com.mysema.commons.lang.Assert;
import com.soap.moon.global.common.BaseTimeEntity;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.tomcat.jni.Address;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "member")
public class Member extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Embedded
    private Account account;

    @Embedded
    private Password password;

    @Column(name = "name", length = 4, nullable = false)
    private String name;

    @Embedded
    private Email email;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 20)
    private MemberStatus status;

    @Column(name = "last_login_at")
    private LocalDateTime lastLoginAt;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", length = 20)
    private RoleStatus roleStatus;

    @Builder
    public Member(Account account, Password password, String name, Email email, Address address,
        MemberStatus status, LocalDateTime lastLoginAt, RoleStatus roleStatus) {
        Assert.notNull(account, "account must not be null");
        Assert.notNull(password, "password must not be null");
        Assert.notNull(name, "name must not be null");
        Assert.notNull(email, "email must not be null");
        Assert.notNull(status, "status must not be null");
        Assert.notNull(lastLoginAt, "lastLoginAt must not be null");
        Assert.notNull(roleStatus, "roleStatus must not be null");

        this.account = account;
        this.password = password;
        this.name = name;
        this.email = email;
        this.status = status;
        this.lastLoginAt = lastLoginAt;
        this.roleStatus = roleStatus;
    }

}
