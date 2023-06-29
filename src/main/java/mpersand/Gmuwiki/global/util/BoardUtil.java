package mpersand.Gmuwiki.global.util;

import lombok.RequiredArgsConstructor;
import mpersand.Gmuwiki.domain.board.entity.Board;
import mpersand.Gmuwiki.domain.board.exception.BoardNotFoundException;
import mpersand.Gmuwiki.domain.board.repository.BoardRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BoardUtil {

    private final BoardRepository boardRepository;

    public Board findBoardById(Long id) {

        return boardRepository.findById(id)
                .orElseThrow(() -> new BoardNotFoundException());
    }
}