package mpersand.Gmuwiki.domain.auth.sevice;

import lombok.RequiredArgsConstructor;
import mpersand.Gmuwiki.domain.auth.entity.RefreshToken;
import mpersand.Gmuwiki.domain.auth.presentation.dto.request.LoginRequest;
import mpersand.Gmuwiki.domain.auth.presentation.dto.response.LoginResponse;
import mpersand.Gmuwiki.domain.auth.repository.RefreshTokenRepository;
import mpersand.Gmuwiki.domain.user.entity.User;
import mpersand.Gmuwiki.global.annotation.AnnotationExceptionService;
import mpersand.Gmuwiki.global.security.jwt.TokenProvider;
import mpersand.Gmuwiki.global.security.jwt.properties.JwtProperties;
import mpersand.Gmuwiki.global.util.UserUtil;

@RequiredArgsConstructor
@AnnotationExceptionService
public class UserLoginService {

    private final UserUtil userUtil;
    private final TokenProvider tokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtProperties jwtProperties;

    public LoginResponse execute(LoginRequest loginRequest) {

        User user = userUtil.getUserByEmail(loginRequest.getEmail());

        userUtil.checkPassword(user, loginRequest.getPassword());

        String accessToken = tokenProvider.generatedAccessToken(loginRequest.getEmail());
        String refreshToken = tokenProvider.generatedRefreshToken(loginRequest.getEmail());
        RefreshToken refreshTokenEntity = new RefreshToken(loginRequest.getEmail(), refreshToken,tokenProvider.getREFRESH_TOKEN_EXPIRE_TIME());

        refreshTokenRepository.save(refreshTokenEntity);

        return LoginResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .expiredAt(tokenProvider.getExpiredAtToken(accessToken, jwtProperties.getAccessSecret()))
                .build();
    }
}
