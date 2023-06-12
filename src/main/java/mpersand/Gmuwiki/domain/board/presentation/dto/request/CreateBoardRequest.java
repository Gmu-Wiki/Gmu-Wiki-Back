package mpersand.Gmuwiki.domain.board.presentation.dto.request;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CreateBoardRequest {

    @NotBlank(message = "제목은 필수 입력값입니다.")
    private String title;

    private String content;

    @NotBlank(message = "글 카테고리는 필수 선택값입니다.")
    private String boardType;

    @NotBlank(message = "글 세부 카테고리는 필수 선택값입니다.")
    private String boardDetailType;
}

