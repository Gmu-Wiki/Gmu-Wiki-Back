package mpersand.Gmuwiki.domain.board.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import mpersand.Gmuwiki.domain.board.entity.Board;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
public class ResentBoardResponse {

    private Long id;

    private String title;

    private LocalDateTime editedDate;

    public static ResentBoardResponse toResponse(Board board) {

        return ResentBoardResponse.builder()
                .id(board.getId())
                .title(board.getTitle())
                .editedDate(board.getEditedDate())
                .build();
    }
}
