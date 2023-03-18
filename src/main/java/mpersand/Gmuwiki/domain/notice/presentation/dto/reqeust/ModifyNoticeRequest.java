package mpersand.Gmuwiki.domain.notice.presentation.dto.reqeust;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class ModifyNoticeRequest {
    @NotBlank(message = "제목은 필수 입력값입니다")
    private String title;

    @NotBlank(message = "내용은 필수 입력값입니다")
    private String content;


}
