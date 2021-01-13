package com.soap.moon.domains.member.domain;

import com.mysema.commons.lang.Assert;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Email {

    @javax.validation.constraints.Email
    @Column(name ="email", nullable = false, unique = true)
    private String email;

    @Builder //비밀번호 암호화 추가
    public Email(String email){
        Assert.notNull(email, "email must not be null!!");
        this.email = email;
    }

}
