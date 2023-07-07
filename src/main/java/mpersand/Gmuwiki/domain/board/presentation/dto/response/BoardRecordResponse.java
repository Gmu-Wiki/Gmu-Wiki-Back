package mpersand.Gmuwiki.domain.board.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import mpersand.Gmuwiki.domain.board.entity.BoardRecord;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
public class BoardRecordResponse {

    private Long id;

    private LocalDateTime createdDate;

    private String name;

    public static BoardRecordResponse toResponse(BoardRecord boardRecord) {

        return BoardRecordResponse.builder()
                .id(boardRecord.getId())
                .createdDate(boardRecord.getCreatedDate())
                .name(boardRecord.getName())
                .build();
    }
}