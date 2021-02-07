package com.soap.moon.domains.user.domain;


import com.mysema.commons.lang.Assert;
import com.soap.moon.global.common.BaseTimeEntity;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Parent;

@Entity
@Table(name = "user_oauth")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class UserOauth extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "provider")
    @Enumerated(EnumType.STRING)
    private SocialLoginType socialLoginType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    public User user;

    @Builder
    public UserOauth(User user, SocialLoginType socialLoginType) {
        Assert.notNull(socialLoginType, "socialLoginType must not be null");
        Assert.notNull(user, "user must not be null");

        this.socialLoginType = socialLoginType;
        this.user = user;
    }

}


