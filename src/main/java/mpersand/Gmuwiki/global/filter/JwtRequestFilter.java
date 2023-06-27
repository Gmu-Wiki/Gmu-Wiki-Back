package mpersand.Gmuwiki.global.filter;

import lombok.RequiredArgsConstructor;
import mpersand.Gmuwiki.global.security.jwt.TokenProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtRequestFilter extends OncePerRequestFilter {

    private final TokenProvider tokenProvider;

    private final Logger log = LoggerFactory.getLogger(getClass().getSimpleName());

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String token = tokenProvider.resolveToken(request);

        if(token != null && !token.isBlank()) {

            Authentication authentication = tokenProvider.authentication(token);

            SecurityContextHolder.getContext().setAuthentication(authentication);

            log.info("current user email = " + authentication.getName());
        }

        filterChain.doFilter(request, response);
    }
}
