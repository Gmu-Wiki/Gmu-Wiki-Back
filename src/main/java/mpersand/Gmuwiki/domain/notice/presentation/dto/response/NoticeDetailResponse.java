package mpersand.Gmuwiki.domain.notice.presentation.dto.response;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class NoticeDetailResponse {

    private Long id;

    private String title;

    private String content;

    private LocalDateTime createdDate;

    private LocalDateTime editedDate;
}
