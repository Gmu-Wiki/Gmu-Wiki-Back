package mpersand.Gmuwiki.domain.auth.repository;

import mpersand.Gmuwiki.domain.auth.entity.RefreshToken;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface RefreshTokenRepository extends CrudRepository<RefreshToken, String> {

    RefreshToken findByToken(String token);

    RefreshToken findByUserId(UUID userId);
}
