package mpersand.Gmuwiki.domain.board.service;

import lombok.RequiredArgsConstructor;
import mpersand.Gmuwiki.domain.board.entity.Board;
import mpersand.Gmuwiki.domain.board.entity.BoardRecord;
import mpersand.Gmuwiki.domain.board.presentation.dto.response.ListBoardRecordResponse;
import mpersand.Gmuwiki.domain.board.repository.BoardRecordRepository;
import mpersand.Gmuwiki.global.annotation.ReadOnlyService;
import mpersand.Gmuwiki.global.util.BoardUtil;

import java.util.List;
import java.util.stream.Collectors;

import static mpersand.Gmuwiki.domain.board.presentation.dto.response.BoardRecordResponse.toResponse;


@RequiredArgsConstructor
@ReadOnlyService
public class ListBoardRecordService {

    private final BoardRecordRepository boardRecordRepository;

    private final BoardUtil boardUtil;

    public ListBoardRecordResponse execute(Long id) {

        Board board = boardUtil.findBoardById(id);

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
