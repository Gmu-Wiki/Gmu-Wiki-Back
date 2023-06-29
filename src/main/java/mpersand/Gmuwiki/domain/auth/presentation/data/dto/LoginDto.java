package mpersand.Gmuwiki.domain.auth.presentation.data.dto;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LoginDto {

    private String code;
}