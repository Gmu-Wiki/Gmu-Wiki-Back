package mpersand.Gmuwiki.domain.auth.repository;

import mpersand.Gmuwiki.domain.auth.entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, String> {
}
