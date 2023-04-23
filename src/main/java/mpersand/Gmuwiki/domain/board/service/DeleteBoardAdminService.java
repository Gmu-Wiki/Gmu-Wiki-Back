package mpersand.Gmuwiki.domain.board.service;

import lombok.RequiredArgsConstructor;
import mpersand.Gmuwiki.domain.board.entity.Board;
import mpersand.Gmuwiki.domain.board.exception.BoardNotFoundException;
import mpersand.Gmuwiki.domain.board.repository.BoardRepository;
import mpersand.Gmuwiki.global.annotation.ExceptionServiceAnnotation;

@RequiredArgsConstructor
@ExceptionServiceAnnotation
public class DeleteBoardAdminService {

    private final BoardRepository boardRepository;

    public void execute(Long id) {

        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new BoardNotFoundException());

        boardRepository.delete(board);
    }
}
