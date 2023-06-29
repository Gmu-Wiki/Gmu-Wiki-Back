package mpersand.Gmuwiki.global.security.auth;

import lombok.RequiredArgsConstructor;
import mpersand.Gmuwiki.domain.user.entity.User;
import mpersand.Gmuwiki.domain.user.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) {
        User user = userRepository.findByEmail(userName)
                .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다."));
        return new AuthDetails(user);
    }
}
