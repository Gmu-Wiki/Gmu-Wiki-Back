package mpersand.Gmuwiki.domain.board.service;

import lombok.RequiredArgsConstructor;
import mpersand.Gmuwiki.domain.board.entity.Board;
import mpersand.Gmuwiki.domain.board.exception.BoardNotFoundException;
import mpersand.Gmuwiki.domain.board.exception.NotMyBoardException;
import mpersand.Gmuwiki.domain.board.repository.BoardRepository;
import mpersand.Gmuwiki.domain.user.entity.User;
import mpersand.Gmuwiki.global.util.UserUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DeleteBoardService {

    private final BoardRepository boardRepository;

    private final UserUtil userUtil;

    @Transactional(rollbackFor = Exception.class)
    public void execute(Long id) {

        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new BoardNotFoundException());

        User user = userUtil.currentUser();

        if(!board.getUser().getEmail().equals(user.getEmail())) {
            throw new NotMyBoardException();
        }

        boardRepository.delete(board);
    }
}
