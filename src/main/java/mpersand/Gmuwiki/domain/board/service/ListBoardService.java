package mpersand.Gmuwiki.domain.board.service;

import lombok.RequiredArgsConstructor;
import mpersand.Gmuwiki.domain.board.entity.Board;
import mpersand.Gmuwiki.domain.board.enums.BoardType;
import mpersand.Gmuwiki.domain.board.presentation.dto.response.BoardResponse;
import mpersand.Gmuwiki.domain.board.presentation.dto.response.ListBoardResponse;
import mpersand.Gmuwiki.domain.board.repository.BoardRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ListBoardService {

    private final BoardRepository boardRepository;

    public ListBoardResponse execute(BoardType boardType) {

        List<Board> boards = boardRepository.findByBoardType(boardType);

        ListBoardResponse listBoardResponse = ListBoardResponse.builder()
                .list(
                        boards.stream()
                                .map(board -> toResponse(board))
                                .collect(Collectors.toList())
                )
                .build();

        return listBoardResponse;
    }

    private BoardResponse toResponse(Board board) {

        return BoardResponse.builder()
                .title(board.getTitle())
                .build();
    }
}
