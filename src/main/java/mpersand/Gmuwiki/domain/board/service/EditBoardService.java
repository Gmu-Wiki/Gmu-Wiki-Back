package mpersand.Gmuwiki.domain.board.service;

import lombok.RequiredArgsConstructor;
import mpersand.Gmuwiki.domain.board.entity.Board;
import mpersand.Gmuwiki.domain.board.entity.BoardRecord;
import mpersand.Gmuwiki.domain.board.exception.BoardNotChangeException;
import mpersand.Gmuwiki.domain.board.exception.ExistTitleException;
import mpersand.Gmuwiki.domain.board.presentation.dto.request.EditBoardRequest;
import mpersand.Gmuwiki.domain.board.repository.BoardRecordRepository;
import mpersand.Gmuwiki.domain.board.repository.BoardRepository;
import mpersand.Gmuwiki.domain.user.entity.User;
import mpersand.Gmuwiki.global.annotation.RollbackService;
import mpersand.Gmuwiki.global.util.BoardUtil;
import mpersand.Gmuwiki.global.util.UserUtil;

@RequiredArgsConstructor
@RollbackService
public class EditBoardService {

    private final BoardRepository boardRepository;

    private final BoardRecordRepository boardRecordRepository;

    private final BoardUtil boardUtil;

    private final UserUtil userUtil;

    public void execute(Long id, EditBoardRequest editBoardRequest) {

        Board board = boardUtil.findBoardById(id);

        User user = userUtil.currentUser();

        if (board.getTitle().equals(editBoardRequest.getTitle()) && board.getContent().equals(editBoardRequest.getContent())) {
            throw new BoardNotChangeException();
        } else if (!board.getTitle().equals(editBoardRequest.getTitle()) && boardRepository.existsByTitle(editBoardRequest.getTitle())) {
            throw new ExistTitleException();
        }

        BoardRecord boardRecord = BoardRecord.builder()
                .title(board.getTitle())
                .content(board.getContent())
                .name(board.getName())
                .boardType(board.getBoardType())
                .createdDate(board.getCreatedDate())
                .editedDate(board.getEditedDate())
                .board(board)
                .build();

        boardRecordRepository.save(boardRecord);

        board.update(editBoardRequest, user.getName());
    }
}