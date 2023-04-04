package mpersand.Gmuwiki.domain.notice.presentation.dto.response;

import lombok.*;

@Getter
@AllArgsConstructor
@Builder
public class NoticeResponse {
    private Long id;
    private String title;
    private String name;
}
