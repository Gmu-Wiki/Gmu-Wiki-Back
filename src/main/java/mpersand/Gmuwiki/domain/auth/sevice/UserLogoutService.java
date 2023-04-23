package mpersand.Gmuwiki.domain.auth.sevice;

import lombok.RequiredArgsConstructor;
import mpersand.Gmuwiki.domain.auth.entity.BlackList;
import mpersand.Gmuwiki.domain.auth.entity.RefreshToken;
import mpersand.Gmuwiki.domain.auth.exception.BlackListAlreadyExistException;
import mpersand.Gmuwiki.domain.auth.exception.RefreshTokenNotFoundException;
import mpersand.Gmuwiki.domain.auth.repository.BlackListRepository;
import mpersand.Gmuwiki.domain.auth.repository.RefreshTokenRepository;
import mpersand.Gmuwiki.domain.user.entity.User;
import mpersand.Gmuwiki.global.annotation.ExceptionServiceAnnotation;
import mpersand.Gmuwiki.global.security.jwt.TokenProvider;
import mpersand.Gmuwiki.global.util.UserUtil;

@RequiredArgsConstructor
@ExceptionServiceAnnotation
public class UserLogoutService {

    private final UserUtil userUtil;
    private final RefreshTokenRepository refreshTokenRepository;
    private final BlackListRepository blackListRepository;
    private final TokenProvider tokenProvider;

    public void execute(String accessToken) {
        User user = userUtil.currentUser();
        String email = user.getEmail();
        RefreshToken refreshToken = refreshTokenRepository.findById(email)
                .orElseThrow(() -> new RefreshTokenNotFoundException());
        refreshTokenRepository.delete(refreshToken);
        saveBlackList(user.getEmail(), accessToken);
    }

    private void saveBlackList(String email, String accessToken) {

        if(blackListRepository.existsById(accessToken)) {
            throw new BlackListAlreadyExistException();
        }

        long expiredTime = tokenProvider.getACCESS_TOKEN_EXPIRE_TIME();

        BlackList blackList = BlackList.builder()
                .email(email)
                .accessToken(accessToken)
                .timeToLive(expiredTime)
                .build();

        blackListRepository.save(blackList);
    }
}
