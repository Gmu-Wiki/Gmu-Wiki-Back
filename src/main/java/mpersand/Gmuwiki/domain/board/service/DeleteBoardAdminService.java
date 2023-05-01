package mpersand.Gmuwiki.domain.board.service;

import lombok.RequiredArgsConstructor;
import mpersand.Gmuwiki.domain.board.entity.Board;
import mpersand.Gmuwiki.domain.board.exception.BoardNotFoundException;
import mpersand.Gmuwiki.domain.board.repository.BoardRecordRepository;
import mpersand.Gmuwiki.domain.board.repository.BoardRepository;
import mpersand.Gmuwiki.global.annotation.RollbackService;

@RequiredArgsConstructor
@RollbackService
public class DeleteBoardAdminService {

    private final BoardRepository boardRepository;

    private final BoardRecordRepository boardRecordRepository;

    public void execute(Long id) {

        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new BoardNotFoundException());

        boardRecordRepository.deleteAllByBoard(board);

        boardRepository.delete(board);
    }
}
