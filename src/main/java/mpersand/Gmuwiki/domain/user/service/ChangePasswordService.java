package mpersand.Gmuwiki.domain.user.service;

import lombok.RequiredArgsConstructor;
import mpersand.Gmuwiki.domain.auth.exception.MisMatchPasswordException;
import mpersand.Gmuwiki.domain.user.entity.User;
import mpersand.Gmuwiki.domain.user.presentation.dto.request.ChangePasswordRequest;
import mpersand.Gmuwiki.global.annotation.RollbackService;
import mpersand.Gmuwiki.global.util.UserUtil;
import org.springframework.security.crypto.password.PasswordEncoder;

@RequiredArgsConstructor
@RollbackService
public class ChangePasswordService {

    private final PasswordEncoder passwordEncoder;
    private final UserUtil userUtil;

    public void execute(ChangePasswordRequest changePasswordRequest) {

        User user = userUtil.currentUser();

        if (passwordIsNotMatch(changePasswordRequest, user))
            throw new MisMatchPasswordException();

        user.updatePassword(passwordEncoder.encode(changePasswordRequest.getNewPassword()));
    }

    private boolean passwordIsNotMatch(ChangePasswordRequest changePasswordRequest, User user) {
        return !passwordEncoder.matches(changePasswordRequest.getPassword(), user.getPassword());
    }
}
