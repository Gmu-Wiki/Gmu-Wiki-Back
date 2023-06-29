package mpersand.Gmuwiki.global.security.jwt.properties;

import io.jsonwebtoken.security.Keys;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

import java.security.Key;

@ConfigurationProperties(prefix = "jwt")
@ConstructorBinding
public class JwtProperties {

    private final Key accessSecret;
    private final Key refreshSecret;

    public JwtProperties(String accessSecret, String refreshSecret) {
        this.accessSecret = Keys.hmacShaKeyFor(accessSecret.getBytes());
        this.refreshSecret = Keys.hmacShaKeyFor(refreshSecret.getBytes());
    }

    public Key getAccessSecret() {
        return accessSecret;
    }

    public Key getRefreshSecret() {
        return refreshSecret;
    }
}

