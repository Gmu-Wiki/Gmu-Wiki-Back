package mpersand.Gmuwiki.global.security.config;

import lombok.RequiredArgsConstructor;
import mpersand.Gmuwiki.global.filter.ExceptionFilter;
import mpersand.Gmuwiki.global.filter.JwtRequestFilter;
import mpersand.Gmuwiki.global.logger.filter.LogRequestFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
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

                .antMatchers(HttpMethod.POST, "/auth").permitAll()
                .antMatchers(HttpMethod.PATCH, "/auth").permitAll()
                .antMatchers(HttpMethod.DELETE, "/auth").authenticated()

                .antMatchers("/file/**").authenticated()

                .antMatchers(HttpMethod.POST, "/board").authenticated()
                .antMatchers(HttpMethod.GET, "/board/search").authenticated()
                .antMatchers(HttpMethod.GET, "/board/recent").authenticated()
                .antMatchers(HttpMethod.GET, "/board").authenticated()
                .antMatchers(HttpMethod.GET, "/board/{id}/record").authenticated()
                .antMatchers(HttpMethod.GET, "/board/{id}/record/detail").authenticated()
                .antMatchers(HttpMethod.GET, "/board/{id}").authenticated()
                .antMatchers(HttpMethod.DELETE, "/board/{id}").authenticated()
                .antMatchers(HttpMethod.PATCH, "/board/{id}").authenticated()
                .antMatchers(HttpMethod.DELETE, "/board/{id}/record").hasAuthority("ROLE_ADMIN")

                .antMatchers(HttpMethod.POST, "/inquiry").authenticated()
                .antMatchers(HttpMethod.GET, "/inquiry").hasAuthority("ROLE_ADMIN")
                .antMatchers(HttpMethod.GET, "/inquiry/{id}").hasAuthority("ROLE_ADMIN")
                .antMatchers(HttpMethod.POST, "/inquiry/approve/{id}").hasAuthority("ROLE_ADMIN")
                .antMatchers(HttpMethod.POST, "/inquiry/refusal/{id}").hasAuthority("ROLE_ADMIN")

                .antMatchers(HttpMethod.GET, "/notice").authenticated()
                .antMatchers(HttpMethod.GET, "/notice/{id}").authenticated()
                .antMatchers(HttpMethod.POST, "/notice").hasAuthority("ROLE_ADMIN")
                .antMatchers(HttpMethod.PATCH, "/notice/{id}").hasAuthority("ROLE_ADMIN")
                .antMatchers(HttpMethod.DELETE, "/notice/{id}").hasAuthority("ROLE_ADMIN")

                .antMatchers("/role/**").hasAuthority("ROLE_ADMIN")

                .anyRequest().denyAll();

        http
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(exceptionFilter, JwtRequestFilter.class)
                .addFilterBefore(logRequestFilter, ExceptionFilter.class);

        return http.build();
    }
}
