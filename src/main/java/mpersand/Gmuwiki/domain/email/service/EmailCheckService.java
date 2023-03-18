package mpersand.Gmuwiki.domain.email.service;

import lombok.RequiredArgsConstructor;
import mpersand.Gmuwiki.domain.email.entity.EmailAuth;
import mpersand.Gmuwiki.domain.email.exception.MisMatchAuthCodeException;
import mpersand.Gmuwiki.domain.email.repository.EmailAuthRepository;
import mpersand.Gmuwiki.global.util.EmailUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class EmailCheckService {

    private final EmailAuthRepository emailAuthRepository;
    private final EmailUtil emailUtil;

    @Transactional(rollbackFor = Exception.class)
    public void execute(String email, String authCode) {
        EmailAuth emailAuth = emailUtil.getEmailEntityById(email);
        checkAuthCode(emailAuth, authCode);
        emailAuth.updateAuthentication(true);
        emailAuthRepository.save(emailAuth);
    }

    private void checkAuthCode(EmailAuth emailAuth, String authCode) {
        if (!Objects.equals(emailAuth.getRandomValue(), authCode)) {
            throw new MisMatchAuthCodeException();
        }
    }
}
