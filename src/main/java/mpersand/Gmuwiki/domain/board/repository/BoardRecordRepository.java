package mpersand.Gmuwiki.domain.board.repository;

import mpersand.Gmuwiki.domain.board.entity.BoardRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRecordRepository extends JpaRepository<BoardRecord, Long> {

    boolean existsByTitle(String title);
}
