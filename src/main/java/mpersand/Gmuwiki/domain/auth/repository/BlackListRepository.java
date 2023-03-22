package mpersand.Gmuwiki.domain.auth.repository;

import mpersand.Gmuwiki.domain.auth.entity.BlackList;
import org.springframework.data.repository.CrudRepository;

public interface BlackListRepository extends CrudRepository<BlackList, String> {
}
