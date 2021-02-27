package com.soap.moon.domains.user.domain;

import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.cache.CacheProperties.Redis;
import org.springframework.data.redis.core.RedisHash;

@Data
@RedisHash
public class RedisToken implements Serializable {
    private static final long serialVersionUID = -7353484588260422449L;

    public RedisToken(){}

    private String username;
    private String refreshToken;
    private String accessToken;

}
