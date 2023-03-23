package mpersand.Gmuwiki.global.util;

import lombok.RequiredArgsConstructor;
import mpersand.Gmuwiki.domain.email.entity.EmailAuth;
import mpersand.Gmuwiki.domain.email.exception.NotVerifyEmailException;
import mpersand.Gmuwiki.domain.email.repository.EmailAuthRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmailUtil {

    private final EmailAuthRepository emailAuthRepository;

    public EmailAuth getEmailEntityById(String email) {
        return emailAuthRepository.findById(email)
                .orElseThrow(() -> new NotVerifyEmailException());
    }

    public void checkEmailAuthentication(EmailAuth emailAuth) {
        if(!emailAuth.isAuthentication()) {
            throw new NotVerifyEmailException();
        }
    }
}