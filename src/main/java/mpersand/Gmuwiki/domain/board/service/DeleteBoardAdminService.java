package mpersand.Gmuwiki.domain.board.service;

import lombok.RequiredArgsConstructor;
import mpersand.Gmuwiki.domain.board.entity.Board;
import mpersand.Gmuwiki.domain.board.exception.BoardNotFoundException;
import mpersand.Gmuwiki.domain.board.repository.BoardRecordRepository;
import mpersand.Gmuwiki.domain.board.repository.BoardRepository;
import mpersand.Gmuwiki.domain.file.entity.BoardFile;
import mpersand.Gmuwiki.domain.file.repository.BoardFileRepository;
import mpersand.Gmuwiki.domain.file.service.BoardFileService;
import mpersand.Gmuwiki.global.annotation.RollbackService;

import java.util.List;

@RequiredArgsConstructor
@RollbackService
public class DeleteBoardAdminService {

    private final BoardRepository boardRepository;

    private final BoardRecordRepository boardRecordRepository;

    private final BoardFileRepository boardFileRepository;

    private final BoardFileService boardFileService;

    public void execute(Long id) {

        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new BoardNotFoundException());

        List<BoardFile> boardFiles = boardFileRepository.getBoardFileByBoardId(id);

        if(boardFiles != null) {

            for(BoardFile boardFile : boardFiles) {

                boardFileService.deleteFile(boardFile.getUrl().substring(54));

                boardFileRepository.deleteAllByBoard(board);
            }
        }

        boardRecordRepository.deleteAllByBoard(board);

        boardRepository.delete(board);
    }
}