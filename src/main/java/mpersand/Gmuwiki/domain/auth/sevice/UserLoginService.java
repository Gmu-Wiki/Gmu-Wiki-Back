package mpersand.Gmuwiki.domain.auth.sevice;

import gauth.GAuth;
import gauth.GAuthToken;
import gauth.GAuthUserInfo;
import lombok.RequiredArgsConstructor;
import mpersand.Gmuwiki.domain.auth.exception.RoleNotExistException;
import mpersand.Gmuwiki.domain.auth.presentation.data.dto.LoginDto;
import mpersand.Gmuwiki.domain.auth.presentation.data.response.LoginResponse;
import mpersand.Gmuwiki.domain.auth.util.AuthUtil;
import mpersand.Gmuwiki.domain.user.entity.User;
import mpersand.Gmuwiki.domain.user.enums.Role;
import mpersand.Gmuwiki.domain.user.repository.UserRepository;
import mpersand.Gmuwiki.global.annotation.RollbackService;
import mpersand.Gmuwiki.global.gauth.properties.GAuthProperties;
import mpersand.Gmuwiki.global.security.jwt.TokenProvider;

import java.io.IOException;
import java.time.ZonedDateTime;

@RequiredArgsConstructor
@RollbackService
public class UserLoginService {

    private final GAuthProperties gAuthProperties;

    private final UserRepository userRepository;

    private final TokenProvider tokenProvider;

    private final GAuth gAuth;

    private final AuthUtil authUtil;

    public LoginResponse execute(LoginDto loginDto) throws IOException {

        GAuthToken gAuthToken = gAuth.generateToken(loginDto.getCode(), gAuthProperties.getClientId(), gAuthProperties.getClientSecret(), gAuthProperties.getRedirectUri());

        GAuthUserInfo gAuthUserInfo = gAuth.getUserInfo(gAuthToken.getAccessToken());

        Role role = getRoleByGAuthInfo(gAuthUserInfo.getRole(), gAuthUserInfo.getEmail());

        String accessToken = tokenProvider.generateAccessToken(gAuthUserInfo.getEmail(), role);

        String refreshToken = tokenProvider.generateRefreshToken(gAuthUserInfo.getEmail(), role);

        ZonedDateTime accessExp = tokenProvider.accessExpiredTime();

        ZonedDateTime refreshExp = tokenProvider.refreshExpiredTime();

        if(role == Role.ROLE_ADMIN) {

            createAdminOrRefreshToken(gAuthUserInfo, refreshToken);
        } else {

            createUserOrRefreshToken(gAuthUserInfo, refreshToken);
        }

        return LoginResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .accessExp(accessExp)
                .refreshExp(refreshExp)
                .build();
    }

    private Role getRoleByGAuthInfo(String role, String email) {

        User user = userRepository.findUserByEmail(email);

        if(user == null) {
            switch (role) {
                case "ROLE_STUDENT":
                    return Role.ROLE_STUDENT;
                case "ROLE_ADMIN":
                    return Role.ROLE_ADMIN;
                default:
                    throw new RoleNotExistException();
            }
        }

        if(user.getRole().equals(Role.ROLE_ADMIN)) {

            return Role.ROLE_ADMIN;
        }
        return Role.ROLE_STUDENT;
    }

    private void createUserOrRefreshToken(GAuthUserInfo gAuthUserInfo, String refreshToken) {

        User userInfo = userRepository.findUserByEmail(gAuthUserInfo.getEmail());

        if(userInfo == null) {

            authUtil.saveNewUser(gAuthUserInfo, refreshToken);
        } else {

            authUtil.saveNewRefreshToken(userInfo, refreshToken);
        }
    }

    private void createAdminOrRefreshToken(GAuthUserInfo gAuthUserInfo, String refreshToken) {

        User adminInfo = userRepository.findUserByEmail(gAuthUserInfo.getEmail());

        if(adminInfo == null) {

            authUtil.saveNewAdmin(gAuthUserInfo, refreshToken);
        } else {

            authUtil.saveNewRefreshToken(adminInfo, refreshToken);
        }
    }
}
