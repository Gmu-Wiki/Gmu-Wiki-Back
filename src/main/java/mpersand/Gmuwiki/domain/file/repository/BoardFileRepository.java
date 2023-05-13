package mpersand.Gmuwiki.domain.file.repository;

import mpersand.Gmuwiki.domain.board.entity.Board;
import mpersand.Gmuwiki.domain.file.entity.BoardFile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardFileRepository extends JpaRepository<BoardFile, Long> {

    void deleteAllByBoard(Board board);

    List<BoardFile> getBoardFileByBoardId(Long id);
}