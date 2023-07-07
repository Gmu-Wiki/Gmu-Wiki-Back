package mpersand.Gmuwiki.domain.notice.presentation.dto.response;

import lombok.*;
import mpersand.Gmuwiki.domain.notice.entity.Notice;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@Builder
public class NoticeResponse {

    private Long id;

    private String title;

    private LocalDateTime createdDate;

    public static NoticeResponse toResponse(Notice notice) {

        return NoticeResponse.builder()
                .id(notice.getId())
                .title(notice.getTitle())
                .createdDate(notice.getCreatedDate())
                .build();
    }
}
