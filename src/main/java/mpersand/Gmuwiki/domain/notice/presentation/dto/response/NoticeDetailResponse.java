package mpersand.Gmuwiki.domain.notice.presentation.dto.response;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class NoticeDetailResponse {

    private Long id;
    private String title;
    private String name;
    private String content;

}
