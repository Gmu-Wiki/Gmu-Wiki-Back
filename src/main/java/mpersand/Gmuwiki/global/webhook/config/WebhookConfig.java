package mpersand.Gmuwiki.global.webhook.config;

import okhttp3.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebhookConfig {

    private final OkHttpClient httpClient = new OkHttpClient();

    @Bean
    public OkHttpClient httpClient() {
        return httpClient;
    }

}