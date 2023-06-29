package mpersand.Gmuwiki.domain.auth.sevice;

import lombok.RequiredArgsConstructor;
import mpersand.Gmuwiki.domain.auth.entity.RefreshToken;
import mpersand.Gmuwiki.domain.auth.repository.RefreshTokenRepository;
import mpersand.Gmuwiki.domain.user.entity.User;
import mpersand.Gmuwiki.domain.user.exception.UserNotFoundException;
import mpersand.Gmuwiki.global.annotation.RollbackService;
import mpersand.Gmuwiki.global.util.UserUtil;

@RollbackService
@RequiredArgsConstructor
public class UserLogoutService {

    private final UserUtil userUtil;

    private final RefreshTokenRepository refreshTokenRepository;

    public void execute() {

        User userInfo = userUtil.currentUser();

        RefreshToken refreshToken = refreshTokenRepository.findByUserId(userInfo.getId());

        if(refreshToken == null) {

            throw new UserNotFoundException();
        }

        refreshTokenRepository.delete(refreshToken);
    }
}
