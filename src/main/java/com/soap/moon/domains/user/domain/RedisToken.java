package com.soap.moon.domains.user.domain;

import java.io.Serializable;
import lombok.Data;
import org.springframework.data.redis.core.RedisHash;

@Data
@RedisHash
public class RedisToken implements Serializable {
    private static final long serialVersionUID = -7353484588260422449L;

    private String username;
    private String refreshToken;

}
