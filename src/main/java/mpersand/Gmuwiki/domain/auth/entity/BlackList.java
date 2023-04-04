package mpersand.Gmuwiki.domain.auth.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@RedisHash(value = "blackList")
public class BlackList {

    @Id
    private String accessToken;

    private String email;

    @TimeToLive
    private Long timeToLive;
}
