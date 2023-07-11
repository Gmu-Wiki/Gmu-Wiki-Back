package mpersand.Gmuwiki.domain.board.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import mpersand.Gmuwiki.domain.board.entity.Board;

@Getter
@Builder
@AllArgsConstructor
public class SearchBoardResponse {

    private Long id;

    private String title;

    public static SearchBoardResponse toResponse(Board board) {

        return SearchBoardResponse.builder()
                .id(board.getId())
                .title(board.getTitle())
                .build();
    }
}