package mpersand.Gmuwiki.domain.notice.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class NoticeResponse {

    private Long id;
    private String title;
    private String content;
}
