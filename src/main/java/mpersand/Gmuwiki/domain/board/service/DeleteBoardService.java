package mpersand.Gmuwiki.domain.board.service;

import lombok.RequiredArgsConstructor;
import mpersand.Gmuwiki.domain.board.entity.Board;
import mpersand.Gmuwiki.domain.board.exception.BoardNotFoundException;
import mpersand.Gmuwiki.domain.board.exception.BoardAuthorMismatchException;
import mpersand.Gmuwiki.domain.board.repository.BoardRepository;
import mpersand.Gmuwiki.domain.user.entity.User;
import mpersand.Gmuwiki.global.annotation.ExceptionServiceAnnotation;
import mpersand.Gmuwiki.global.util.UserUtil;

@RequiredArgsConstructor
@ExceptionServiceAnnotation
public class DeleteBoardService {

    private final BoardRepository boardRepository;

    private final UserUtil userUtil;

    public void execute(Long id) {

        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new BoardNotFoundException());

        User user = userUtil.currentUser();

        if(!(board.getUser() == user)) {
            throw new BoardAuthorMismatchException();
        }

        boardRepository.delete(board);
    }
}
