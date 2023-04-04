package mpersand.Gmuwiki.domain.user.presentation.dto.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChangePasswordRequest {

    @NotBlank
    private String password;

    @NotBlank
    @Pattern(regexp = "(?=.*\\W)(?=\\S+$).{8,16}", message = "비밀번호는 특수문자가 1개 이상 포함되어야 하고 글자 수는 8자 이상, 16자 이내로 입력해야 합니다.")
    private String newPassword;

}
