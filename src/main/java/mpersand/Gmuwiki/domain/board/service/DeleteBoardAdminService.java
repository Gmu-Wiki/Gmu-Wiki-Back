package mpersand.Gmuwiki.domain.board.service;

import lombok.RequiredArgsConstructor;
import mpersand.Gmuwiki.domain.board.entity.Board;
import mpersand.Gmuwiki.domain.board.repository.BoardRecordRepository;
import mpersand.Gmuwiki.domain.board.repository.BoardRepository;
import mpersand.Gmuwiki.global.annotation.RollbackService;
import mpersand.Gmuwiki.global.util.BoardUtil;

@RequiredArgsConstructor
@RollbackService
public class DeleteBoardAdminService {

    private final BoardRepository boardRepository;

    private final BoardRecordRepository boardRecordRepository;

    private final BoardUtil boardUtil;

    public void execute(Long id) {

        Board board = boardUtil.findBoardById(id);

        boardRecordRepository.deleteAllByBoard(board);

        boardRepository.delete(board);
    }
}