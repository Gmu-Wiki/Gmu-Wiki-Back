package mpersand.Gmuwiki.global.security.jwt;

import io.jsonwebtoken.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import mpersand.Gmuwiki.domain.auth.exception.RoleNotExistException;
import mpersand.Gmuwiki.domain.user.enums.Role;
import mpersand.Gmuwiki.global.security.auth.AuthDetailsService;
import mpersand.Gmuwiki.global.security.exception.TokenExpirationException;
import mpersand.Gmuwiki.global.security.exception.TokenNotValidException;
import mpersand.Gmuwiki.global.security.jwt.properties.JwtProperties;
import mpersand.Gmuwiki.global.security.jwt.properties.TokenTimeProperties;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.security.Key;
import java.time.ZonedDateTime;
import java.util.Date;


@Getter
@Component
@RequiredArgsConstructor
public class TokenProvider {

    private final JwtProperties jwtProperties;

    private final TokenTimeProperties tokenTimeProperties;

    private final AuthDetailsService authDetailsService;

    @AllArgsConstructor
    private enum TokenObject {
        ACCESS_TYPE("access"),
        REFRESH_TYPE("refresh"),
        TOKEN_PREFIX("Bearer "),
        AUTHORITY("authority");
        String value;
    }

    public ZonedDateTime accessExpiredTime() {

        return ZonedDateTime.now().plusSeconds(tokenTimeProperties.getAccessTime());
    }

    public ZonedDateTime refreshExpiredTime() {

        return ZonedDateTime.now().plusSeconds(tokenTimeProperties.getRefreshTime());
    }

    public String generateAccessToken(String email, Role role) {

        return generateToken(email, TokenObject.ACCESS_TYPE.value, jwtProperties.getAccessSecret(), tokenTimeProperties.getAccessTime(), role);
    }

    public String generateRefreshToken(String email, Role role) {

        return generateToken(email, TokenObject.REFRESH_TYPE.value, jwtProperties.getRefreshSecret(), tokenTimeProperties.getRefreshTime(), role);
    }

    public Authentication authentication(String token) {

        UserDetails userDetails = authDetailsService.loadUserByUsername(getTokenSubject(token, jwtProperties.getAccessSecret()));

        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public String resolveToken(HttpServletRequest request) {

        String token = request.getHeader("Authorization");

        if(token == null) {
            return null;
        }

        return parseToken(token);
    }

    public String exactEmailFromRefreshToken(String refresh) {
        return getTokenSubject(refresh, jwtProperties.getRefreshSecret());
    }

    public Role exactRoleFromRefreshToken(String refresh) {

        String authority = getTokenBody(refresh, jwtProperties.getRefreshSecret()).get(TokenObject.AUTHORITY.value, String.class);

        switch (authority) {

            case "ROLE_STUDENT":
                return Role.ROLE_STUDENT;

            case "ROLE_ADMIN":
                return Role.ROLE_ADMIN;

            default:
                throw new RoleNotExistException();
        }
    }

    public String generateToken(String email, String type, Key secret, Long exp, Role role) {

        final Claims claims = Jwts.claims().setSubject(email);

        claims.put("type", type);

        claims.put(TokenObject.AUTHORITY.value, role);

        return Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .signWith(secret, SignatureAlgorithm.HS256)
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + exp * 1000))
                .compact();
    }

    public String parseToken(String token) {

        if(token.startsWith(TokenObject.TOKEN_PREFIX.value)) {

            return token.replace(TokenObject.TOKEN_PREFIX.value, "");

        } else {

            return null;
        }
    }

    private Claims getTokenBody(String token, Key secret) {

        try {

            return Jwts.parserBuilder()
                    .setSigningKey(secret)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

        } catch (ExpiredJwtException e) {

            throw new TokenExpirationException();

        } catch (JwtException e) {

            throw new TokenNotValidException();
        }
    }

    private String getTokenSubject(String token, Key secret) {

        return getTokenBody(token, secret).getSubject();
    }
}