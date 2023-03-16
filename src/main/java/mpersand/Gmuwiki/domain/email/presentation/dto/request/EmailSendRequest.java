package mpersand.Gmuwiki.domain.email.presentation.dto.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
public class EmailSendRequest {

    @Email
    @NotBlank(message = "이메일은 필수 입력 값입니다.")
    private final String email;

    @JsonCreator
    public EmailSendRequest(@JsonProperty("email") String email) {
        this.email = email;
    }
}
