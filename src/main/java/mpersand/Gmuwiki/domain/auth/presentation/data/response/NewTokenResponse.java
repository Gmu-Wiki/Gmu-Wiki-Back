package mpersand.Gmuwiki.domain.auth.presentation.data.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.ZonedDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class NewTokenResponse {

    private String accessToken;

    private String refreshToken;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private ZonedDateTime accessExp;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private ZonedDateTime refreshExp;
}
