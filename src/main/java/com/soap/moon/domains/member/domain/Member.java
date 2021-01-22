package com.soap.moon.domains.member.domain;

import com.mysema.commons.lang.Assert;
import com.soap.moon.global.common.BaseTimeEntity;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    @Column(name = "name", nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private MemberStatus status;

    @Column(name = "last_login_at")
    private LocalDateTime lastLoginAt;

//    @Enumerated(EnumType.STRING)
//    @Column(name = "role", length = 20)
//    private RoleStatus roleStatus;

    //    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    @JoinTable(
//        name = "Member_Authority",
//        joinColumns = @JoinColumn(name = "user_id"),
//        inverseJoinColumns = @JoinColumn(name = "authority_id")
//    )
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private Set<MemberAuthority> authorities = new HashSet<>();

    @Builder
    public Member(Account account, Password password, String name,
        MemberStatus status, LocalDateTime lastLoginAt) {
        Assert.notNull(account, "account must not be null");
        Assert.notNull(password, "password must not be null");
        Assert.notNull(name, "name must not be null");
        Assert.notNull(status, "status must not be null");
        Assert.notNull(lastLoginAt, "lastLoginAt must not be null");

        this.account = account;
        this.password = password;
        this.name = name;
        this.status = status;
        this.lastLoginAt = lastLoginAt;
    }

    public void addAuthority(MemberAuthority memberAuthority) {
        authorities.add(memberAuthority);
        memberAuthority.setMember(this);
    }

}
