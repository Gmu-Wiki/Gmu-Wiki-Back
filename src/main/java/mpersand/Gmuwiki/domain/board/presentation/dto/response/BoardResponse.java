package mpersand.Gmuwiki.domain.board.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import mpersand.Gmuwiki.domain.board.entity.Board;
import mpersand.Gmuwiki.domain.board.enums.BoardDetailType;

@Getter
@Builder
@AllArgsConstructor
public class BoardResponse {

    private String title;

    private BoardDetailType boardDetailType;

    public static BoardResponse toResponse(Board board) {

        return BoardResponse.builder()
                .title(board.getTitle())
                .boardDetailType(board.getBoardDetailType())
                .build();
    }
}
