package mpersand.Gmuwiki;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableCaching
@EnableScheduling
@EnableJpaAuditing
@EnableJpaRepositories
@SpringBootApplication
@ConfigurationPropertiesScan
@EnableConfigurationProperties
public class GMuwikiApplication {

	public static void main(String[] args) {
		SpringApplication.run(GMuwikiApplication.class, args);
	}

}
