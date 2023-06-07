package mpersand.Gmuwiki.domain.user.repository;

import mpersand.Gmuwiki.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findByEmail(String email);

    User findUserByEmail(String email);
}
