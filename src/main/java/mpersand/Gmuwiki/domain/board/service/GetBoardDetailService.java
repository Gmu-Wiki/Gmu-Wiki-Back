package mpersand.Gmuwiki.domain.board.service;

import lombok.RequiredArgsConstructor;
import mpersand.Gmuwiki.domain.board.entity.Board;
import mpersand.Gmuwiki.domain.board.presentation.dto.response.DetailBoardResponse;
import mpersand.Gmuwiki.global.annotation.ReadOnlyService;
import mpersand.Gmuwiki.global.util.BoardUtil;

@RequiredArgsConstructor
@ReadOnlyService
public class GetBoardDetailService {

    private final BoardUtil boardUtil;

    public DetailBoardResponse execute(Long id) {

        Board board = boardUtil.findBoardById(id);

        DetailBoardResponse detailBoardResponse = DetailBoardResponse.builder()
                .id(board.getId())
                .title(board.getTitle())
                .content(board.getContent())
                .createdDate(board.getCreatedDate())
                .editedDate(board.getEditedDate())
                .build();

        return detailBoardResponse;
    }
}
