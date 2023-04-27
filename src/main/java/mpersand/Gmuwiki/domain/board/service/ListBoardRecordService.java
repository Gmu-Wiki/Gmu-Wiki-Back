package mpersand.Gmuwiki.domain.board.service;

import lombok.RequiredArgsConstructor;
import mpersand.Gmuwiki.domain.board.entity.Board;
import mpersand.Gmuwiki.domain.board.entity.BoardRecord;
import mpersand.Gmuwiki.domain.board.exception.BoardNotFoundException;
import mpersand.Gmuwiki.domain.board.presentation.dto.response.ListBoardRecordResponse;
import mpersand.Gmuwiki.domain.board.repository.BoardRecordRepository;
import mpersand.Gmuwiki.domain.board.repository.BoardRepository;
import mpersand.Gmuwiki.global.annotation.ReadOnlyService;

import java.util.List;
import java.util.stream.Collectors;

import static mpersand.Gmuwiki.domain.board.presentation.dto.response.BoardRecordResponse.toResponse;


@RequiredArgsConstructor
@ReadOnlyService
public class ListBoardRecordService {

    private final BoardRecordRepository boardRecordRepository;

    private final BoardRepository boardRepository;

    public ListBoardRecordResponse execute(Long id) {

        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new BoardNotFoundException());

        List<BoardRecord> boardRecords = boardRecordRepository.findByBoard(board);

        ListBoardRecordResponse listBoardRecordResponse = ListBoardRecordResponse.builder()
                .boardRecordList(
                        boardRecords.stream()
                                .map(boardRecord -> toResponse(boardRecord))
                                .collect(Collectors.toList())
                )
                .build();

        return listBoardRecordResponse;
    }
}
