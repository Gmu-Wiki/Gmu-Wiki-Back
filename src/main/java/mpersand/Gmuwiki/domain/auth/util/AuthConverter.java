package mpersand.Gmuwiki.domain.auth.util;

import gauth.GAuthUserInfo;
import mpersand.Gmuwiki.domain.auth.entity.RefreshToken;
import mpersand.Gmuwiki.domain.auth.presentation.data.dto.LoginDto;
import mpersand.Gmuwiki.domain.auth.presentation.data.request.LoginRequest;
import mpersand.Gmuwiki.domain.user.entity.User;
import mpersand.Gmuwiki.domain.user.enums.Role;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class AuthConverter {

    public LoginDto toDto(LoginRequest loginRequest) {

        LoginDto loginDto = LoginDto.builder()
                .code(loginRequest.getCode())
                .build();

        return loginDto;
    }


    public User toEntity(GAuthUserInfo gAuthUserInfo) {

        User user = User.builder()
                .id(UUID.randomUUID())
                .email(gAuthUserInfo.getEmail())
                .name(gAuthUserInfo.getName())
                .grade(gAuthUserInfo.getGrade())
                .classNum(gAuthUserInfo.getClassNum())
                .stuNum(gAuthUserInfo.getNum())
                .role(Role.ROLE_STUDENT)
                .build();

        return user;
    }

    public User toGraduateEntity(GAuthUserInfo gAuthUserInfo) {

        User user = User.builder()
                .id(UUID.randomUUID())
                .email(gAuthUserInfo.getEmail())
                .name(gAuthUserInfo.getName())
                .grade(gAuthUserInfo.getGrade())
                .classNum(gAuthUserInfo.getClassNum())
                .stuNum(gAuthUserInfo.getNum())
                .role(Role.ROLE_GRADUATE)
                .build();

        return user;
    }

    public User toAdminEntity(GAuthUserInfo gAuthUserInfo) {

        User user = User.builder()
                .id(UUID.randomUUID())
                .email(gAuthUserInfo.getEmail())
                .name(gAuthUserInfo.getName())
                .grade(gAuthUserInfo.getGrade())
                .classNum(gAuthUserInfo.getClassNum())
                .stuNum(gAuthUserInfo.getNum())
                .role(Role.ROLE_ADMIN)
                .build();

        return user;
    }

    public RefreshToken toEntity(User userInfo, String refreshToken) {
        RefreshToken token = RefreshToken.builder()
                .userId(userInfo.getId())
                .token(refreshToken)
                .build();

        return token;
    }

    public RefreshToken toEntity(UUID userId, String refreshToken) {
        RefreshToken token = RefreshToken.builder()
                .userId(userId)
                .token(refreshToken)
                .build();

        return token;
    }
}