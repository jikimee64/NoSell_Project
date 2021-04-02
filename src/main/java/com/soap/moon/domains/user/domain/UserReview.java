package com.soap.moon.domains.user.domain;

import com.mysema.commons.lang.Assert;
import com.soap.moon.global.common.BaseTimeEntity;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "user_review")
public class UserReview extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "writer_id")
    private Long writerId;

    @Column(name = "writer")
    private String writer;

    @Column(name = "text")
    private String content;

    @Column(name = "good")
    private int good;

    @Column(name = "bad")
    private int bad;

    @Column(name = "stars")
    private float stars;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    public UserReview(String writer, Long writerId, String content,
        float stars, User user) {
        Assert.notNull(writer, "writer must not be null");
        Assert.notNull(writerId, "writerId must not be null");
        Assert.notNull(content, "content must not be null");
        Assert.notNull(stars, "stars must not be null");
        Assert.notNull(user, "user must not be null");

        this.writer = writer;
        this.writerId = writerId;
        this.content = content;
        this.stars = stars;
        this.user = user;
    }


}
