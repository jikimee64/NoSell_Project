package com.soap.moon.domains.member.domain;

import com.mysema.commons.lang.Assert;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_authority_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "authority_id")
    private Authority authority;

    @Builder
    public UserAuthority(User user, Authority authority) {
        Assert.notNull(user, "member must not be null!!");
        Assert.notNull(authority, "authority must not be null!!");
        this.user = user;
        this.authority = authority;
    }

    public void setUser(User user){
        this.user = user;
    }

}
