package mpersand.Gmuwiki.domain.notice.presentation.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class NoticeIdResponse {
    private Long id;
    private String title;
    private String name;
    private String content;
}
