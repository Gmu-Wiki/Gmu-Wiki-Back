package mpersand.Gmuwiki.domain.auth.sevice;

import lombok.RequiredArgsConstructor;
import mpersand.Gmuwiki.domain.auth.entity.RefreshToken;
import mpersand.Gmuwiki.domain.auth.presentation.data.response.NewTokenResponse;
import mpersand.Gmuwiki.domain.auth.repository.RefreshTokenRepository;
import mpersand.Gmuwiki.domain.auth.util.AuthConverter;
import mpersand.Gmuwiki.domain.user.entity.User;
import mpersand.Gmuwiki.domain.user.enums.Role;
import mpersand.Gmuwiki.domain.user.exception.UserNotFoundException;
import mpersand.Gmuwiki.domain.user.repository.UserRepository;
import mpersand.Gmuwiki.global.annotation.RollbackService;
import mpersand.Gmuwiki.global.security.exception.RefreshTokenExpirationException;
import mpersand.Gmuwiki.global.security.exception.TokenNotValidException;
import mpersand.Gmuwiki.global.security.jwt.TokenProvider;

import java.time.ZonedDateTime;

@RollbackService
@RequiredArgsConstructor
public class TokenReissueService {

    private final TokenProvider tokenProvider;

    private final RefreshTokenRepository refreshTokenRepository;

    private final AuthConverter authConverter;

    private final UserRepository userRepository;

    public NewTokenResponse execute(String refreshToken) {

        String refresh = tokenProvider.parseToken(refreshToken);

        if(refresh == null) {
            throw new TokenNotValidException();
        }

        String email = tokenProvider.exactEmailFromRefreshToken(refresh);

        Role role = tokenProvider.exactRoleFromRefreshToken(refresh);

        RefreshToken existingRefreshToken = refreshTokenRepository.findById(refresh)
                .orElseThrow(()->new RefreshTokenExpirationException());

        String newAccessToken = tokenProvider.generateAccessToken(email, role);

        String newRefreshToken = tokenProvider.generateRefreshToken(email, role);

        ZonedDateTime accessExp = tokenProvider.accessExpiredTime();

        ZonedDateTime refreshExp = tokenProvider.refreshExpiredTime();

        RefreshToken newRefreshTokenEntity = authConverter.toEntity(existingRefreshToken.getUserId(), newRefreshToken);

        refreshTokenRepository.save(newRefreshTokenEntity);

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException());

        return NewTokenResponse.builder()
                .accessToken(newAccessToken)
                .refreshToken(newRefreshToken)
                .accessExp(accessExp)
                .refreshExp(refreshExp)
                .build();
    }

}