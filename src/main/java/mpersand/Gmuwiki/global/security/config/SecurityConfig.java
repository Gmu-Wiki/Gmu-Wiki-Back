package mpersand.Gmuwiki.global.security.config;

import lombok.RequiredArgsConstructor;
import mpersand.Gmuwiki.global.filter.ExceptionFilter;
import mpersand.Gmuwiki.global.filter.JwtRequestFilter;
import mpersand.Gmuwiki.global.logger.filter.LogRequestFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsUtils;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {

    private final JwtRequestFilter jwtRequestFilter;

    private final ExceptionFilter exceptionFilter;

    private final LogRequestFilter logRequestFilter;

    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .formLogin().disable()
                .httpBasic().disable()
                .cors().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
                .antMatchers("/auth/**").permitAll()
                .antMatchers("/file/**").hasAnyAuthority("ROLE_STUDENT", "ROLE_ADMIN")
                .antMatchers("/user/**").hasAuthority("ROLE_STUDENT")
                .antMatchers("/admin/**").hasAuthority("ROLE_ADMIN")
                .anyRequest().denyAll();

        http
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(exceptionFilter, JwtRequestFilter.class)
                .addFilterBefore(logRequestFilter, ExceptionFilter.class);

        return http.build();
    }
}
