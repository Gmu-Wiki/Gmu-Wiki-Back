package mpersand.Gmuwiki.domain.auth.util;

import gauth.GAuthUserInfo;
import lombok.AllArgsConstructor;
import mpersand.Gmuwiki.domain.auth.entity.RefreshToken;
import mpersand.Gmuwiki.domain.auth.repository.RefreshTokenRepository;
import mpersand.Gmuwiki.domain.user.entity.User;
import mpersand.Gmuwiki.domain.user.repository.UserRepository;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AuthUtil {

    private final RefreshTokenRepository refreshTokenRepository;

    private final AuthConverter authConverter;

    private final UserRepository userRepository;

    public void saveNewUser(GAuthUserInfo gAuthUserInfo, String refreshToken) {

        User signInUserInfo = authConverter.toEntity(gAuthUserInfo);

        userRepository.save(signInUserInfo);

        saveNewRefreshToken(signInUserInfo, refreshToken);
    }

    public void saveNewGraduate(GAuthUserInfo gAuthUserInfo, String refreshToken) {

        User signInGraduateInfo = authConverter.toGraduateEntity(gAuthUserInfo);

        userRepository.save(signInGraduateInfo);

        saveNewRefreshToken(signInGraduateInfo, refreshToken);
    }

    public void saveNewAdmin(GAuthUserInfo gAuthUserInfo, String refreshToken) {

        User signInAdminInfo = authConverter.toAdminEntity(gAuthUserInfo);

        userRepository.save(signInAdminInfo);

        saveNewRefreshToken(signInAdminInfo, refreshToken);
    }

    public RefreshToken saveNewRefreshToken(User userInfo, String refreshToken) {

        RefreshToken newRefreshToken = authConverter.toEntity(userInfo, refreshToken);

        return refreshTokenRepository.save(newRefreshToken);
    }
}