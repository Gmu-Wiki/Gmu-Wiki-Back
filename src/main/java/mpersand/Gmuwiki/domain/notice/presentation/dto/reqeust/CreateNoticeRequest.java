package mpersand.Gmuwiki.domain.notice.presentation.dto.reqeust;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateNoticeRequest {
    @NotBlank(message = "제목은 필수 입력값입니다")
    private String title;

    @NotBlank(message = "내용은 필수 입력값입니다")
    private String content;

    @NotBlank(message = "이름은 필수입력값입니다")
    private String name;
}
