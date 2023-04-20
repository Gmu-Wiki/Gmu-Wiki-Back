package mpersand.Gmuwiki.domain.board.service;

import lombok.RequiredArgsConstructor;
import mpersand.Gmuwiki.domain.board.entity.Board;
import mpersand.Gmuwiki.domain.board.exception.BoardNotFoundException;
import mpersand.Gmuwiki.domain.board.repository.BoardRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AdminDeleteBoardService {

    private final BoardRepository boardRepository;

    @Transactional(rollbackFor = Exception.class)
    public void execute(Long id) {

        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new BoardNotFoundException());

        boardRepository.delete(board);
    }
}
