package com.soap.moon.domains.member.domain;

import com.mysema.commons.lang.Assert;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "Authority")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Authority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="authority_id")
    private Long id;

    @Column(name = "authority_name", length = 50, nullable = false)
    private String authorityName;

    @Builder
    public Authority(String authorityName){
        Assert.notNull(authorityName, "authorityName must not be null!!");
        this.authorityName = authorityName;
    }

}
