package mpersand.Gmuwiki.domain.auth.sevice;

import lombok.RequiredArgsConstructor;
import mpersand.Gmuwiki.domain.auth.exception.ExistEmailException;
import mpersand.Gmuwiki.domain.auth.presentation.dto.request.SignUpRequest;
import mpersand.Gmuwiki.domain.email.entity.EmailAuth;
import mpersand.Gmuwiki.domain.user.entity.User;
import mpersand.Gmuwiki.domain.user.enums.Role;
import mpersand.Gmuwiki.domain.user.repository.UserRepository;
import mpersand.Gmuwiki.global.annotation.ExceptionServiceAnnotation;
import mpersand.Gmuwiki.global.util.EmailUtil;
import org.springframework.security.crypto.password.PasswordEncoder;

@RequiredArgsConstructor
@ExceptionServiceAnnotation
public class UserSignUpService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final EmailUtil emailUtil;

    public void execute(SignUpRequest signUpRequest) {

        if(userRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new ExistEmailException();
        }

        EmailAuth emailAuth = emailUtil.getEmailEntityById(signUpRequest.getEmail());

        emailUtil.checkEmailAuthentication(emailAuth);

        User user = User.builder()
                .email(signUpRequest.getEmail())
                .password(passwordEncoder.encode(signUpRequest.getPassword()))
                .name(signUpRequest.getName())
                .number(signUpRequest.getNumber())
                .role(Role.from(signUpRequest.getRole()))
                .build();

        userRepository.save(user);
    }

}
