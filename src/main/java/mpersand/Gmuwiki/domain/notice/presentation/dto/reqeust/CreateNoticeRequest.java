package mpersand.Gmuwiki.domain.notice.presentation.dto.reqeust;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@Builder
@RequiredArgsConstructor
public class CreateNoticeRequest {
    @NotBlank(message = "제목은 필수 입력값입니다")
    private final String title;
    @NotBlank(message = "내용은 필수 입력값입니다")
    private final String content;
    @NotBlank(message = "이름은 필수입력값입니다")
    private final String name;
}
