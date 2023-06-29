package mpersand.Gmuwiki.domain.auth.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.util.UUID;

@Getter
@Builder
@RedisHash(value = "refreshToken", timeToLive = 60L * 60 * 24 * 7)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class RefreshToken {

    @Indexed
    @Id
    private String token;

    @Indexed
    private UUID userId;
}
