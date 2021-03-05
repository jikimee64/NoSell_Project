package com.soap.moon.domains.user.domain;

import com.mysema.commons.lang.Assert;
import com.soap.moon.domains.category.domain.CategoryUser;
import com.soap.moon.global.common.BaseTimeEntity;
import com.soap.moon.global.config.converter.BooleanToYNConverter;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "user", uniqueConstraints = {
    @UniqueConstraint(columnNames = "email")
})
@EqualsAndHashCode
public class User extends BaseTimeEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Embedded
    private Account account;

    @Embedded
    private Password password;

    @Column(name = "phone_num", nullable = false)
    private String phoneNum;

    @Column(name = "nickname", nullable = false)
    private String nickName;

    @Column(name = "profile_image")
    private String profileImage;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private UserStatus status;

    @Convert(converter=BooleanToYNConverter.class)
    private boolean active;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<UserAuthority> authorities = new HashSet<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<CategoryUser> categoryUsers = new HashSet<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<UserReview> userReviews = new HashSet<>();

    @Builder
    public User(Account account, Password password, String nickName, String phoneNum,
        String profileImage, UserStatus status, boolean active) {
        Assert.notNull(account, "account must not be null");
        Assert.notNull(password, "password must not be null");
        Assert.notNull(phoneNum, "phoneNum must not be null");
        Assert.notNull(nickName, "nickName must not be null");
        Assert.notNull(profileImage, "profileImage must not be null");
        Assert.notNull(status, "status must not be null");
        Assert.notNull(active, "active must not be null");

        this.account = account;
        this.password = password;
        this.phoneNum = phoneNum;
        this.nickName = nickName;
        this.profileImage = profileImage;
        this.status = status;
        this.active = active;
    }

    public void addAuthority(UserAuthority userAuthority) {
        authorities.add(userAuthority);
        userAuthority.setUser(this);
    }

    public void changeNickname(String nickName){
        this.nickName = nickName;
    }

    public void changePassword(Password password){
        this.password = password;
    }

    public void changeActive(Boolean active){
        this.active = active;
    }

}
