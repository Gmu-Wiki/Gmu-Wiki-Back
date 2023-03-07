package mpersand.Gmuwiki.domain.auth.presentation.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SignUpRequest {

    @NotBlank(message = "이메일은 필수 입력 값입니다.")
    private String email;

    @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
    @Pattern(regexp = "(?=.*\\W)(?=\\S+$).{8,16}", message = "비밀번호는 특수문자가 1개 이상 포함되어야 하고 글자 수는 8자 이상, 16자 이내로 입력해야 합니다.")
    private String password;

    @NotBlank(message = "이름은 필수 입력 값입니다.")
    private String name;

    @NotBlank(message = "학번은 필수 입력 값입니다.")
    private String number;

    @NotBlank(message = "역할은 필수 입력 값입니다.")
    private String role;
}
