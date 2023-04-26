package mpersand.Gmuwiki.domain.board.service;

import lombok.RequiredArgsConstructor;
import mpersand.Gmuwiki.domain.board.entity.Board;
import mpersand.Gmuwiki.domain.board.entity.BoardRecord;
import mpersand.Gmuwiki.domain.board.exception.BoardNotFoundException;
import mpersand.Gmuwiki.domain.board.exception.ExistTitleException;
import mpersand.Gmuwiki.domain.board.presentation.dto.request.EditBoardRequest;
import mpersand.Gmuwiki.domain.board.repository.BoardRepository;
import mpersand.Gmuwiki.domain.board.repository.BoardRecordRepository;
import mpersand.Gmuwiki.global.annotation.RollbackService;

@RequiredArgsConstructor
@RollbackService
public class EditBoardService {

    private final BoardRepository boardRepository;

    private final BoardRecordRepository boardRecordRepository;

    public void execute(Long id, EditBoardRequest editBoardRequest) {

        Board board = boardRepository.findById(id)
                .orElseThrow(()-> new BoardNotFoundException());

        if(boardRecordRepository.existsByTitle(editBoardRequest.getTitle())) {
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

        board.update(editBoardRequest);
    }
}