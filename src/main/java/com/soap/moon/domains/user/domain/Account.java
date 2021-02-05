package com.soap.moon.domains.user.domain;

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
public class Account {

    @javax.validation.constraints.Email
    @Column(name = "email", nullable = false, updatable=false)
    private String email;

    @Builder
    public Account(String email){
        Assert.notNull(email, "email must not be null!!");
        this.email = email;
    }

}
