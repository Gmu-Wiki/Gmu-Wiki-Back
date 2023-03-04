package mpersand.Gmuwiki.domain.user.repository;

import mpersand.Gmuwiki.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
