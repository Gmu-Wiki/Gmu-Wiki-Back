package mpersand.Gmuwiki.domain.board.presentation.dto.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EditBoardRequest {

    @NotBlank(message = "제목은 필수 입력값입니다")
    private String title;

    @NotBlank(message = "내용은 필수 입력값입니다")
    private String content;
}
