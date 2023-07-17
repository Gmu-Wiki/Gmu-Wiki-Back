package mpersand.Gmuwiki.domain.board.service;

import lombok.RequiredArgsConstructor;
import mpersand.Gmuwiki.domain.board.entity.Board;
import mpersand.Gmuwiki.domain.board.presentation.dto.response.ListResentBoardResponse;
import mpersand.Gmuwiki.domain.board.repository.BoardRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static mpersand.Gmuwiki.domain.board.presentation.dto.response.ResentBoardResponse.toResponse;

@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
@RequiredArgsConstructor
public class ListRecentEditBoardService {

    private final BoardRepository boardRepository;

    public ListResentBoardResponse execute() {

        List<Board> titles = boardRepository.findRecentlyModifiedBoards();

        ListResentBoardResponse listResentBoardResponse = ListResentBoardResponse.builder()
                .boardTitleList(
                        titles.stream()
                                .map(board -> toResponse(board))
                                .collect(Collectors.toList())
                )
                .build();

        return listResentBoardResponse;
    }
}
