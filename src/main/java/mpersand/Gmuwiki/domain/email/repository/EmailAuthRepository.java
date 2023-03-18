package mpersand.Gmuwiki.domain.email.repository;

import mpersand.Gmuwiki.domain.email.entity.EmailAuth;
import org.springframework.data.repository.CrudRepository;

public interface EmailAuthRepository extends CrudRepository<EmailAuth, String> {
}
