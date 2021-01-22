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
public class Account {

    @javax.validation.constraints.Email
    @Column(name = "user_id", nullable = false, unique = true, updatable=false)
    private String userId;

    @Builder
    public Account(String userId){
        Assert.notNull(userId, "userId must not be null!!");
        this.userId = userId;
    }

}
