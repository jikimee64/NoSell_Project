package com.soap.moon.domains.user.domain;


import com.mysema.commons.lang.Assert;
import com.soap.moon.global.common.BaseTimeEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user_oauth")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class UserOauth extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "provider_type")
    @Enumerated(EnumType.STRING)
    private ProviderType providerType;

    @Column(name = "provider_id")
    private String providerId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    public User user;

    @Builder
    public UserOauth(ProviderType providerType, String providerId, User user) {
        Assert.notNull(providerType, "socialLoginType must not be null");
        Assert.notNull(providerId, "providerId must not be null");
        Assert.notNull(user, "user must not be null");

        this.providerType = providerType;
        this.providerId = providerId;
        this.user = user;
    }

}


