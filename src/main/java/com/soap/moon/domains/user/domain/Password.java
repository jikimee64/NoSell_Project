package com.soap.moon.domains.user.domain;

import com.mysema.commons.lang.Assert;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.LastModifiedDate;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Password {

    @Column(name = "password")
    private String password;

//    @Column(name = "login_fail_count")
//    @ColumnDefault("0")
//    private int loginFailCount;
//
//    @Column(name = "password_updated_at")
//    @LastModifiedDate //??
//    private LocalDateTime passwordUpdatedAt;

    @Builder //비밀번호 암호화 추가
    public Password(final String password) {
        //소셜로그인 경우 패스워드가 필요없기 때문에 null 허용
        //Assert.notNull(password, "password must not be null!!");
        this.password = password;
    }

//    public boolean isMatched(final String rawPassword){
//        if(loginFailCount >= 5)
//            throw new PasswordFailedExceededException();
//
//        final boolean matches = isMatches(rawPasswrd);
//        updateFaileCount(matches);
//        return matches;
//    }
//
//    public void changePassword(final String newPassword, final String oldPassword){
//        if(isMatched(oldPassword)) {
//            value = encodePassword(newPassword);
//            extendExpirationDate();
//        }
//    }

}
