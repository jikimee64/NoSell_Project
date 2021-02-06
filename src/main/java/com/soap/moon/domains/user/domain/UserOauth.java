package com.soap.moon.domains.user.domain;


import com.soap.moon.global.common.BaseTimeEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
    @GeneratedValue
    private Long id;

    private String access_token;

    private String email;

    @Column(name = "expire_time")
    private int expireTime;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "provider")
    @Enumerated(EnumType.STRING)
    private SocialLoginType socialLoginType;

    @Builder
    public UserOauth(String access_token, String email, String imageUrl, int expireTime,
        SocialLoginType socialLoginType) {
        this.access_token = access_token;
        this.email = email;
        this.expireTime = expireTime;
        this.imageUrl = imageUrl;
        this.socialLoginType = socialLoginType;
    }


}
