package mpersand.Gmuwiki.domain.user.service;

import lombok.RequiredArgsConstructor;
import mpersand.Gmuwiki.domain.auth.exception.MisMatchPasswordException;
import mpersand.Gmuwiki.domain.user.entity.User;
import mpersand.Gmuwiki.domain.user.presentation.dto.request.ChangePasswordRequest;
import mpersand.Gmuwiki.global.util.UserUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ChangePasswordService {

    private final PasswordEncoder passwordEncoder;
    private final UserUtil userUtil;

    @Transactional(rollbackFor = Exception.class)
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
