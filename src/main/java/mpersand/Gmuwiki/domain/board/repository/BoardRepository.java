package mpersand.Gmuwiki.domain.board.repository;

import mpersand.Gmuwiki.domain.board.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
}
