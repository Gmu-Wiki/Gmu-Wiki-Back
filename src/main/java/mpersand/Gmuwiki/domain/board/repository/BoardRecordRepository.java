package mpersand.Gmuwiki.domain.board.repository;

import mpersand.Gmuwiki.domain.board.entity.Board;
import mpersand.Gmuwiki.domain.board.entity.BoardRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRecordRepository extends JpaRepository<BoardRecord, Long> {

    List<BoardRecord> findByBoard(Board board);

    boolean existsByTitle(String title);

}
