package mpersand.Gmuwiki.domain.user.service;

import lombok.RequiredArgsConstructor;
import mpersand.Gmuwiki.domain.auth.exception.MisMatchPasswordException;
import mpersand.Gmuwiki.domain.user.entity.User;
import mpersand.Gmuwiki.domain.user.presentation.dto.request.ChangePasswordRequest;
import mpersand.Gmuwiki.domain.user.repository.UserRepository;
import mpersand.Gmuwiki.global.util.EmailUtil;
import mpersand.Gmuwiki.global.util.UserUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ChangePasswordService {

    private final PasswordEncoder passwordEncoder;
    private final UserUtil userUtil;
    private final UserRepository userRepository;

    @Transactional(rollbackFor = Exception.class)
    public void execute(ChangePasswordRequest changePasswordRequest) {

        User user = userUtil.currentUser();

        if (!passwordEncoder.matches(changePasswordRequest.getPassword(), user.getPassword())) {
            throw new MisMatchPasswordException();
        }

        userRepository.save(user.updatePassword(passwordEncoder.encode(changePasswordRequest.getNewPassword())));
    }
}
