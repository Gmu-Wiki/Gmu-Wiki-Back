package mpersand.Gmuwiki.domain.board.service;

import lombok.RequiredArgsConstructor;
import mpersand.Gmuwiki.domain.board.entity.Board;
import mpersand.Gmuwiki.domain.board.presentation.dto.request.SearchBoardTitleRequest;
import mpersand.Gmuwiki.domain.board.presentation.dto.response.ListSearchBoardResponse;
import mpersand.Gmuwiki.domain.board.repository.BoardRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static mpersand.Gmuwiki.domain.board.presentation.dto.response.SearchBoardResponse.toResponse;

@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
@RequiredArgsConstructor
public class ListSearchBoardTitleService {

    private final BoardRepository boardRepository;

    public ListSearchBoardResponse execute(SearchBoardTitleRequest searchBoardTitleRequest) {

        List<Board> titles = boardRepository.findByTitle(searchBoardTitleRequest.getTitle());

        ListSearchBoardResponse listSearchBoardResponse = ListSearchBoardResponse.builder()
                .boardTitleList(
                        titles.stream()
                                .map(board -> toResponse(board))
                                .collect(Collectors.toList())
                )
                .build();

        return listSearchBoardResponse;
    }
}