package com.soap.moon.domains.user.domain;

import com.mysema.commons.lang.Assert;
import com.soap.moon.domains.category.domain.CategoryUser;
import com.soap.moon.global.common.BaseTimeEntity;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
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
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "user", uniqueConstraints = {
    @UniqueConstraint(columnNames = "email")
})
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

    @Column(name = "last_login_at")
    private LocalDateTime lastLoginAt;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<UserAuthority> authorities = new HashSet<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<CategoryUser> categoryUsers = new HashSet<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<UserReview> userReviews = new HashSet<>();

    @Builder
    public User(Account account, Password password, String nickName, String phoneNum,
        String profileImage,
        UserStatus status, LocalDateTime lastLoginAt) {
        Assert.notNull(account, "account must not be null");
        Assert.notNull(password, "password must not be null");
        Assert.notNull(phoneNum, "phoneNum must not be null");
        Assert.notNull(nickName, "nickName must not be null");
        Assert.notNull(profileImage, "profileImage must not be null");
        Assert.notNull(status, "status must not be null");
        Assert.notNull(lastLoginAt, "lastLoginAt must not be null");

        this.account = account;
        this.password = password;
        this.phoneNum = phoneNum;
        this.nickName = nickName;
        this.profileImage = profileImage;
        this.status = status;
        this.lastLoginAt = lastLoginAt;
    }

    public void addAuthority(UserAuthority userAuthority) {
        authorities.add(userAuthority);
        userAuthority.setUser(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(getId(), user.getId()) && Objects
            .equals(getAccount(), user.getAccount()) && Objects
            .equals(getPassword(), user.getPassword()) && Objects
            .equals(getPhoneNum(), user.getPhoneNum()) && Objects
            .equals(getNickName(), user.getNickName()) && Objects
            .equals(getProfileImage(), user.getProfileImage()) && getStatus() == user.getStatus()
            && Objects.equals(getLastLoginAt(), user.getLastLoginAt()) && Objects
            .equals(getAuthorities(), user.getAuthorities());
    }

    @Override
    public int hashCode() {
        return Objects
            .hash(getId(), getAccount(), getPassword(), getPhoneNum(), getNickName(),
                getProfileImage(),
                getStatus(), getLastLoginAt(), getAuthorities());
    }

}
