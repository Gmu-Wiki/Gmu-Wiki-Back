package mpersand.Gmuwiki.domain.notice.presentation.dto.response;

import lombok.*;

import java.util.List;
@Getter
@Builder
@AllArgsConstructor
public class NoticeListResponse {

    private List<NoticeResponse> list;

}
