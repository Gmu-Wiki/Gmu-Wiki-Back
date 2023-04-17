package mpersand.Gmuwiki.domain.board.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import mpersand.Gmuwiki.domain.board.entity.Board;

@Getter
@Builder
@AllArgsConstructor
public class BoardResponse {

    private String title;

    public static BoardResponse toResponse(Board board) {

        return BoardResponse.builder()
                .title(board.getTitle())
                .build();
    }
}
