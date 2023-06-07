package mpersand.Gmuwiki.global.gauth.properties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@Getter
@AllArgsConstructor
@ConstructorBinding
@ConfigurationProperties(prefix = "gauth")
public class GAuthProperties {

    private final String clientId;

    private final String clientSecret;

    private final String redirectUri;
}