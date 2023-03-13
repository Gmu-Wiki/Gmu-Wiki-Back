package mpersand.Gmuwiki.domain.notice.presentation.dto.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@RequiredArgsConstructor
public class ModifyNoticeRequest {

    @NotBlank(message = "수정한 제목이 비어있습니다")
    private final String title;
    @NotBlank(message = "수정한 내용이 비어있습니다")
    private final String content;
    @NotBlank(message = "수정한 이름이 비어있습니다")
    private final String name;


}
