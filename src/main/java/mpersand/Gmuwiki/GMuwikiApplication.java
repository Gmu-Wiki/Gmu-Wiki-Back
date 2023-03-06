package mpersand.Gmuwiki;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableCaching
@EnableConfigurationProperties
@ConfigurationPropertiesScan
@EnableJpaRepositories
public class GMuwikiApplication {

	public static void main(String[] args) {
		SpringApplication.run(GMuwikiApplication.class, args);
	}

}
