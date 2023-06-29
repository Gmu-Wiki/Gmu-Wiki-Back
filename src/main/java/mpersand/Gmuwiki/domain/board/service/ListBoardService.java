package mpersand.Gmuwiki.domain.board.service;

import lombok.RequiredArgsConstructor;
import mpersand.Gmuwiki.domain.board.entity.Board;
import mpersand.Gmuwiki.domain.board.enums.BoardType;
import mpersand.Gmuwiki.domain.board.presentation.dto.response.ListBoardResponse;
import mpersand.Gmuwiki.domain.board.repository.BoardRepository;
import mpersand.Gmuwiki.global.annotation.ReadOnlyService;

import java.util.List;
import java.util.stream.Collectors;

import static mpersand.Gmuwiki.domain.board.presentation.dto.response.BoardResponse.toResponse;

@RequiredArgsConstructor
@ReadOnlyService
public class ListBoardService {

    private final BoardRepository boardRepository;

    public ListBoardResponse execute(BoardType boardType) {

        List<Board> boards = boardRepository.findByBoardType(boardType);

        ListBoardResponse listBoardResponse = ListBoardResponse.builder()
                .boardList(
                        boards.stream()
                                .map(board -> toResponse(board))
                                .collect(Collectors.toList())
                )
                .build();

        return listBoardResponse;
    }
}
