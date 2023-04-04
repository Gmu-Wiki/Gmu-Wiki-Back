package mpersand.Gmuwiki.domain.email.entity;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RedisHash(value = "emailAuth", timeToLive = 60 * 15)
public class EmailAuth {

    @Id
    private String email;

    @Length(max = 6)
    private String randomValue;

    private boolean authentication;

    @ColumnDefault("1")
    private Integer attemptCount;

    public void updateAuthentication(Boolean authentication) {
        this.authentication = authentication;
    }
    public void updateRandomValue(String uuid) {
        this.randomValue = uuid;
    }
    public void increaseAttemptCount() {
        this.attemptCount += 1;
    }
}

