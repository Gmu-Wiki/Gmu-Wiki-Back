package mpersand.Gmuwiki.domain.board.service;

import lombok.RequiredArgsConstructor;
import mpersand.Gmuwiki.domain.board.entity.Board;
import mpersand.Gmuwiki.domain.board.entity.BoardRecord;
import mpersand.Gmuwiki.domain.board.enums.BoardDetailType;
import mpersand.Gmuwiki.domain.board.enums.BoardType;
import mpersand.Gmuwiki.domain.board.exception.ExistTitleException;
import mpersand.Gmuwiki.domain.board.presentation.dto.request.CreateBoardRequest;
import mpersand.Gmuwiki.domain.board.repository.BoardRecordRepository;
import mpersand.Gmuwiki.domain.board.repository.BoardRepository;
import mpersand.Gmuwiki.domain.user.entity.User;
import mpersand.Gmuwiki.global.annotation.RollbackService;
import mpersand.Gmuwiki.global.util.UserUtil;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@RollbackService
public class CreateBoardService {

    private final UserUtil userUtil;

    private final BoardRepository boardRepository;

    private final BoardRecordRepository boardRecordRepository;

    public void execute(CreateBoardRequest createBoardRequest) {

        User user = userUtil.currentUser();

        if(boardRepository.existsByTitle(createBoardRequest.getTitle())) {
            throw new ExistTitleException();
        }

        Board board = Board.builder()
                .title(createBoardRequest.getTitle())
                .content(createBoardRequest.getContent())
                .name(user.getName())
                .boardType(BoardType.from(createBoardRequest.getBoardType()))
                .boardDetailType(BoardDetailType.from(createBoardRequest.getBoardDetailType()))
                .user(user)
                .createdDate(LocalDateTime.now())
                .editedDate(LocalDateTime.now())
                .build();

        BoardRecord boardRecord = BoardRecord.builder()
                .title(board.getTitle())
                .content(board.getContent())
                .name(board.getName())
                .boardType(board.getBoardType())
                .createdDate(board.getCreatedDate())
                .board(board)
                .build();

        boardRepository.save(board);

        boardRecordRepository.save(boardRecord);
    }
}
