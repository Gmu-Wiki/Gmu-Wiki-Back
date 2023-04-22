package mpersand.Gmuwiki.domain.auth.sevice;

import lombok.RequiredArgsConstructor;
import mpersand.Gmuwiki.domain.auth.entity.RefreshToken;
import mpersand.Gmuwiki.domain.auth.exception.RefreshTokenNotFoundException;
import mpersand.Gmuwiki.domain.auth.presentation.dto.response.NewTokenResponse;
import mpersand.Gmuwiki.domain.auth.repository.RefreshTokenRepository;
import mpersand.Gmuwiki.global.annotation.AnnotationExceptionService;
import mpersand.Gmuwiki.global.security.exception.TokenNotValidException;
import mpersand.Gmuwiki.global.security.jwt.TokenProvider;
import mpersand.Gmuwiki.global.security.jwt.properties.JwtProperties;

import java.time.ZonedDateTime;

@RequiredArgsConstructor
@AnnotationExceptionService
public class TokenReissueService {

    private final RefreshTokenRepository refreshTokenRepository;
    private final TokenProvider tokenProvider;
    private final JwtProperties jwtProperties;

    public NewTokenResponse execute(String requestToken) {
        String email = tokenProvider.getUserEmail(requestToken, jwtProperties.getRefreshSecret());
        RefreshToken token = refreshTokenRepository.findById(email)
                .orElseThrow(() -> new RefreshTokenNotFoundException());

        if(!token.getRefreshToken().equals(requestToken)) {
            throw new TokenNotValidException();
        }

        String accessToken = tokenProvider.generatedAccessToken(email);
        String refreshToken = tokenProvider.generatedRefreshToken(email);
        ZonedDateTime expiredAt = tokenProvider.getExpiredAtToken(accessToken, jwtProperties.getAccessSecret());

        token.exchangeRefreshToken(refreshToken);
        refreshTokenRepository.save(token);

        return NewTokenResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .expiredAt(expiredAt)
                .build();
    }
}