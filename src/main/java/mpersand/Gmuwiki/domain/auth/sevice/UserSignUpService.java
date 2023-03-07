package mpersand.Gmuwiki.domain.auth.sevice;

import lombok.RequiredArgsConstructor;
import mpersand.Gmuwiki.domain.auth.exception.ExistEmailException;
import mpersand.Gmuwiki.domain.auth.presentation.dto.request.SignUpRequest;
import mpersand.Gmuwiki.domain.user.entity.User;
import mpersand.Gmuwiki.domain.user.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserSignUpService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Transactional(rollbackFor = Exception.class)
    public void execute(SignUpRequest signUpRequest) {

        if(userRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new ExistEmailException("이미 사용중인 이메일 입니다.");
        }

        User user = User.builder()
                .email(signUpRequest.getEmail())
                .password(passwordEncoder.encode(signUpRequest.getPassword()))
                .name(signUpRequest.getName())
                .number(signUpRequest.getNumber())
                .role(signUpRequest.getRole())
                .build();

        userRepository.save(user);
    }

}
