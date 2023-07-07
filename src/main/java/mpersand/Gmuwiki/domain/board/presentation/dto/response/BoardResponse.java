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

    private Long id;

    private String title;

    private BoardDetailType boardDetailType;

    public static BoardResponse toResponse(Board board) {

        return BoardResponse.builder()
                .id(board.getId())
                .title(board.getTitle())
                .boardDetailType(board.getBoardDetailType())
                .build();
    }
}
